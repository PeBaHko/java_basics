package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static java.lang.System.out;

public class ListLinks extends RecursiveTask<String> {
    private final String url;
    private final String rootUrl;
    private final String tabulator;
    private final String prefix;

    public ListLinks(String url, String tabulator) {
        url = deleteTag(url);
        this.tabulator = tabulator.concat("\t");
        this.rootUrl = getRootUrl(url);
        this.prefix = getPrefix(url);
        this.url = url;
    }
    @Override
    protected String compute() {
  //protected List<String> compute() {
      List<String> list = new ArrayList<>(); //list.addAll(new ArrayList<String>());
        StringBuffer buffer = new StringBuffer();
        String cssQuery = "a[href]";
        int timeout = 200;
        try {
            LocalTime lt = LocalTime.now();
            int hour = lt.getHour();
            int minute = lt.getMinute();
            int second = lt.getSecond();
            out.println("новый поток - "+ hour +":" + minute +":" + second);
            Thread.sleep(timeout);
            Document document = Jsoup.connect(url).get();
            Elements links = document.select(cssQuery);
            //List<ListLinks> list = new ArrayList<>();
            List<LinkAndChild> linkAndChildList = new ArrayList<>();
            links.forEach(link -> {
                String line = link.attr("href");
                ListLinks child;
                if (getTypeLink(line) == 0) {
                    if (comparePaths(url, line)) {
                        child = new ListLinks(getAbsolutLink(rootUrl, getPath(line)), tabulator);
                        linkAndChildList.add(new LinkAndChild(tabulator.concat(deleteTag(getAbsolutLink(rootUrl, getPath(line)))).concat(System.lineSeparator()), child));
                        child.fork();
                    }                  //   out.println();
                } else if (getTypeLink(line) == 1) {
                    if (getRootUrl(line).equalsIgnoreCase(rootUrl)) {
                        if(comparePaths(url, line)) {
                            child = new ListLinks(line, tabulator);
                            linkAndChildList.add(new LinkAndChild(tabulator.concat(deleteTag(line)).concat(System.lineSeparator()), child));
                            child.fork();
                        }
                    }
                }
            });
            linkAndChildList.forEach(lac -> {
                //list.add(lac.getLink());
                buffer.append(lac.getLink());
                //list.addAll(lac.getChild().join());
                buffer.append(lac.getChild().join());
            });
        } catch (IOException | InterruptedException e) {
            out.println("поток прерван");
            return "";
          //return new ArrayList<>();
        }
        out.println("поток окончен");
        return buffer.toString();
        // return new ArrayList<>(list);
    }
    private String  deleteTag(String url) {
        if (url.indexOf("?") > 0) { url = url.substring(0, url.indexOf("?")); }
        if (url.indexOf("#") > 0) { url = url.substring(0, url.indexOf("#")); }
        return url;
    }
    private boolean comparePaths(String url, String link) {
        List<String> pathUrl = getPath(url);
        List<String> path = getPath(link);
        if (pathUrl.size() > path.size()) { return false; }
        boolean isEqual = true;
        for (int i = 0; i < pathUrl.size(); i++) {
            if (!pathUrl.get(i).equalsIgnoreCase(path.get(i))) {
                isEqual = false; break;
            }
        }
        if (pathUrl.size() == path.size()) { return false; }
        return isEqual;
    }
    private int getTypeLink(String link) {
        //  "1" - "Абсолютная ссылка"
        //  "0" - "Относительная ссылка"
        // "-1" - "Другой тип"
        if (link.indexOf("http") == 0) { return 1;
        } else if (link.indexOf("/") == 0) { return 0;
        } else { return -1;
        }
    }
    private List<String> getPath(String link) {
        String[] path = link.split("/");
        ArrayList<String> list = new ArrayList<>();
        if (link.contains("//")) {
            boolean isPath = false;
            for (String line : path) {
                if (line.equalsIgnoreCase(rootUrl)) { isPath = true;
                } else if (isPath) {
                    list.add(line);
                }
            }
        } else {
            for (String line : path) {
                if (!line.equalsIgnoreCase("")) {
                    list.add(line);
                }
            }
        }
        return new ArrayList<>(list);
    }
    private String getAbsolutLink(String url, List<String> path) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append(url);
        for (String line : path) {
            builder.append("/").append(line);
        }
        return builder.toString();
    }
    private String getPrefix(String url) {
        String prefix;
        if (url.contains("https://")&&(url.indexOf("https://")==0)) {
            prefix = "https://";
        } else if (url.contains("http://")&&(url.indexOf("http://")==0)) {
            prefix = "http://";
        } else {
            prefix = "";
        }
        return prefix;
    }
    private String getRootUrl(String url) {
        String doubleRightSlash = "//";
        int start = url.indexOf(doubleRightSlash);
        url = url.substring(start + 2);
        if (url.contains("www")) {
            int i = url.indexOf("www");
            int j = url.substring(i+1).indexOf(".");
            url = url.substring(j+1);
        }
        int stop = url.indexOf("/");
        if (stop > 0) url = url.substring(0, stop);
        return url;
    }
}