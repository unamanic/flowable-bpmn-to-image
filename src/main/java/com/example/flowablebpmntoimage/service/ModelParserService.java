package com.example.flowablebpmntoimage.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.springframework.stereotype.Service;

import com.example.flowablebpmntoimage.util.XmlUtil;

@Service
public class ModelParserService {
    final ProcessEngine processEngine;

    public ModelParserService(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public InputStream BpmnToImage(InputStream modelStream) throws UnsupportedEncodingException, XMLStreamException {
        ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
        InputStreamReader xmlIn = new InputStreamReader(modelStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
        BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(xtr);

        InputStream stream = processEngineConfiguration.getProcessDiagramGenerator().generateDiagram(bpmnModel, "png",
            processEngineConfiguration.getActivityFontName(),
            processEngineConfiguration.getLabelFontName(),
            processEngineConfiguration.getAnnotationFontName(),
            processEngineConfiguration.getClassLoader(),
            processEngineConfiguration.isDrawSequenceFlowNameWithNoLabelDI());

        return stream;
    }
}
