package aoc;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.IntStream;


public class FetchInput {

    private final CloseableHttpClient client;
    private final Resource resource;
    private final Config config;

    public FetchInput(Resource resource, Config config) {
        this.client = HttpClients.createDefault();
        this.resource = resource;
        this.config = config;
    }


    private File getFile(int day, int year) {
        return resource.getResource(year + "/day" + day + ".txt");
    }


    private String clean(String file) {
        return file
                .replace("\r\n", "\n")
                .replace("&gt;", ">");
    }

    public void retrieveInput(int day, int year) {
        File dayFile = getFile(day, year);
        dayFile.getParentFile().mkdirs();
        if (!dayFile.exists()) {
            writeFile(dayFile, clean(doRequest(year + "/day/" + day + "/input")));
        }
    }

    private void writeFile(File file, String content) {
        try {
            FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String doRequest(String path) {
        try {
            HttpGet request = new HttpGet("https://adventofcode.com/" + path);
            request.addHeader("Cookie", String.valueOf(config.getConfigProperty("Cookie")));
            CloseableHttpResponse re = client.execute(request);
            return EntityUtils.toString(re.getEntity());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void downloadIfNotDownloaded(int day, int year) {
        if (!resource.getResource(resource.getDayPath(year, day)).exists()) {
            retrieveInput(day, year);
        }
    }


//    public void retrieveExamples(String day, String year) {
//        var matches = getMatchesByXpath(doRequest(year + "/day/" + day), "//pre/code");
//        for (int i = 0; i < matches.size(); i++) {
//            File file = getFile(day + "-" + (i + 1), year + "-examples");
//            file.getParentFile().mkdirs();
//            if (!file.exists()) {
//                writeFile(file, clean(matches.get(i)));
//            }
//        }
//    }

    //    private List<String> getMatchesByXpath(String html, String xpath) {
//        try {
//            TagNode tagNode = new HtmlCleaner().clean(html);
//            org.w3c.dom.Document doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
//
//            XPath xpathObj = XPathFactory.newInstance().newXPath();
//            NodeList matches = (NodeList) xpathObj.evaluate(xpath, doc, XPathConstants.NODESET);
//            return IntStream.range(0, matches.getLength()).mapToObj(matches::item).map(Node::getTextContent).toList();
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }
}
