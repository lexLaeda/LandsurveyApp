package com.survey.mole.service.file.data;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class DocDataDocumentCreator extends AbstractDataDocumentCreator{

    @Override
    public <T extends Documented> InputStreamResource getDocument(Collection<T> tCollection) {
        String header = getHeader();
        String body = createBody(tCollection);
        String footer = getFooter();
        String document = getFullDocument(header, body, footer);
        try {
            byte[] bytes = IOUtils.resourceToByteArray(document);
            InputStream stream = new ByteArrayInputStream(bytes);
            InputStreamResource inputStreamResource = new InputStreamResource(stream);
            return inputStreamResource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T extends Documented>String createBody(Collection<T> raws){
        return raws.stream()
                .map(doc -> String.join("\t", doc.getRaw()))
                .collect(Collectors.joining("\n"));
    }
    private String getFullDocument(String header, String body, String footer){
        return String.format(Locale.ENGLISH, "%s\n\n%s\n\n%s",header,body,footer);
    }
}
