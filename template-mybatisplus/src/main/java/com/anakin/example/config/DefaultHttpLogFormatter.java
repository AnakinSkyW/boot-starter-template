package com.anakin.example.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.zalando.logbook.*;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


/**
 * @description: Http log格式化
 * @author: anakin
 * @date 2020.8.20 10:53
 */
@Component
public final class DefaultHttpLogFormatter implements HttpLogFormatter {

    enum Component {
        SCHEME, AUTHORITY, PATH, QUERY
    }

    /**
     * Produces an HTTP-like request in individual lines.
     *
     * @param precorrelation the request correlation
     * @param request        the HTTP request
     * @return a line-separated HTTP request
     * @throws IOException if reading body fails
     */
    @Override
    public String format(final Precorrelation precorrelation, final HttpRequest request) throws IOException {
        final String correlationId = precorrelation.getId();
        final String body = request.getBodyAsString();

        final StringBuilder result = new StringBuilder(body.length() + 2048);

        result.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>\n");
        result.append("~Request: ").append(correlationId).append("\n");
        result.append("~").append(request.getMethod()).append(" ");
        reconstruct(request, EnumSet.allOf(Component.class), result);
        result.append(' ');
        result.append(request.getProtocolVersion());
        result.append('\n');

        writeHeaders(request.getHeaders(), result);
        writeBody(body, result);
        result.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>\n");

        return result.toString();
    }

    /**
     * Produces an HTTP-like request in individual lines.
     *
     * @param correlation the request correlation
     * @return a line-separated HTTP request
     * @throws IOException if reading body fails
     * @see StructuredHttpLogFormatter#prepare(Precorrelation, HttpRequest)
     */
    @Override
    public String format(final Correlation correlation, final HttpResponse response) throws IOException {
        final String correlationId = correlation.getId();
        final String body = response.getBodyAsString();

        final StringBuilder result = new StringBuilder(body.length() + 2048);

        result.append("\n<~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        result.append("~Response: ").append(correlationId).append("\n");
        result.append("~Duration: ");
        result.append(correlation.getDuration().toMillis());
        result.append(" ms\n");

        result.append("~").append(response.getProtocolVersion());
        result.append(' ');
        result.append(response.getStatus());
        final String reasonPhrase = response.getReasonPhrase();
        if (reasonPhrase != null) {
            result.append(' ');
            result.append(reasonPhrase);
        }

        result.append('\n');

        writeHeaders(response.getHeaders(), result);
        writeBody(body, result);
        result.append("\n<~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return result.toString();
    }

    private void writeHeaders(final Map<String, List<String>> headers, final StringBuilder output) {
        if (headers.isEmpty()) {
            return;
        }

        for (final Entry<String, List<String>> entry : headers.entrySet()) {
            if ("host".equals(entry.getKey()) || "referer".equals(entry.getKey())
                    || "Content-Type".equals(entry.getKey())
                    || "Duration".equals(entry.getKey())) {
                output.append("~").append(entry.getKey());
                output.append(": ");
                final List<String> headerValues = entry.getValue();
                if (!headerValues.isEmpty()) {
                    for (final String value : entry.getValue()) {
                        output.append(value);
                        output.append(", ");
                    }
                    output.setLength(output.length() - 2); // discard last comma
                }
                output.append('\n');
            }
        }
    }

    private void writeBody(final String body, final StringBuilder output) {
        if (!body.isEmpty()) {
            output.append(StrUtil.maxLength(body, 500));
        } else {
            output.setLength(output.length() - 1); // discard last newline
        }
    }



    private static void reconstruct(final HttpRequest request, final Set<Component> components, StringBuilder url) {
        final String scheme = request.getScheme();
        final String host = request.getHost();
        final Optional<Integer> port = request.getPort();
        final String path = request.getPath();
        final String query = request.getQuery();

        if (components.contains(Component.SCHEME)) {
            url.append(scheme).append(':');
        }

        if (components.contains(Component.AUTHORITY)) {
            url.append("//").append(host);

            port.ifPresent(p -> {
                if (isNotStandardPort(scheme, p)) {
                    url.append(':').append(p);
                }
            });

        } else if (components.contains(Component.SCHEME)) {
            url.append("//");
        }

        if (components.contains(Component.PATH)) {
            url.append(path);
        } else {
            url.append('/');
        }

        if (components.contains(Component.QUERY) && !query.isEmpty()) {
            url.append('?').append(query);
        }
    }

    private static boolean isNotStandardPort(final String scheme, final int port) {
        return ("http".equals(scheme) && port != 80) ||
                ("https".equals(scheme) && port != 443);
    }

}
