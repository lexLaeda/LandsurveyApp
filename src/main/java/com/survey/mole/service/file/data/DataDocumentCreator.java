package com.survey.mole.service.file.data;

import com.survey.mole.model.AbstractEntity;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.util.Collection;

public interface DataDocumentCreator {
    void setHeader(String header);
    void setFooter(String header);
    <T extends Documented> InputStreamResource getDocument(Collection<T> tCollection);
}
