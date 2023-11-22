package com.w.volunteer.common;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(value = "/*",asyncSupported = true)
public class clearFilter extends Filter {
    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {

    }

    @Override
    public String description() {
        return null;
    }
}
