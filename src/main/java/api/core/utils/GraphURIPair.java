/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.core.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.apache.jena.util.SplitIRI;

/**
 *
 * @author tzortzak
 */
public class GraphURIPair {

    private String graph = "";
    private String uri = "";
    
    private String uriPrefix = "";
    private String uriLocalName = "";
    
    private boolean usedAsClass = false;
    
    private int outgoingLinksCount = 0;
    private int outgoingLinksToInstancesCount = 0;
    private int incomingLinksCount = 0;
    
    private ArrayList<String> classes = new ArrayList();
    private ArrayList<String> labels = new ArrayList();
    private HashSet<String> neighborGraphUriPairs = new HashSet();

    
    public GraphURIPair() {
    }
    
    public GraphURIPair(String g, String uri) {
        this.setGraph(g);
        this.setUri(uri);
    }
    
    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph!=null ? graph.trim() : "";
    }
    

    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri =  uri!=null ? uri.trim() : "";
        this.uriPrefix = this.uri.isEmpty()? "" : SplitIRI.namespace(this.uri);
        this.uriLocalName = this.uri.isEmpty()? "" : SplitIRI.localname(this.uri);
    }

    public String getUriPrefix() {
        return uriPrefix;
    }
    
    public String getUriLocalName() {
        return uriLocalName;
    }
    
    public boolean getUsedAsClass() {
        return this.usedAsClass;
    }

    public void setUsedAsClass(boolean usedAsClass) {
        this.usedAsClass = usedAsClass;
    }
    
    
    public int getLinksToInstancesCount() {
        return getOutgoingLinksToInstancesCount() + getIncomingLinksCount();
    }
    
    public int getOutgoingLinksCount() {
        return outgoingLinksCount;
    }

    public void setOutgoingLinksCount(int outgoingLinksCount) {
        this.outgoingLinksCount = outgoingLinksCount>0? outgoingLinksCount : 0;
    }
    public void addOutgoingLink(){
        this.outgoingLinksCount += 1;        
    }

    public int getOutgoingLinksToInstancesCount() {
        return outgoingLinksToInstancesCount;
    }

    public void setOutgoingLinksToInstancesCount(int outgoingLinksToInstancesCount) {
        this.outgoingLinksToInstancesCount = outgoingLinksToInstancesCount>0? outgoingLinksToInstancesCount : 0;
    }
    
    public void addOutgoingLinkToInstance(){
        this.outgoingLinksToInstancesCount += 1;        
    }

    public int getIncomingLinksCount() {
        return incomingLinksCount;
    }


    public void setIncomingLinksCount(int incomingLinksCount) {
        this.incomingLinksCount =  incomingLinksCount>0? incomingLinksCount : 0;;
    }
    public void addIncomingLink(){
        this.incomingLinksCount += 1;
    }

    public HashSet<String> getNeighborGraphUriPairs() {
        return neighborGraphUriPairs;
    }

    public void addNeighborGraphUriPair(String neighborUri) {
        if(neighborUri!=null && !neighborUri.trim().isEmpty()){
            this.neighborGraphUriPairs.add(this.getGraph() + neighborUri.trim());
        }
    }
    
    public void clearNeighborGraphUriPairs() {
        this.neighborGraphUriPairs.clear();
    }
    
    
    public ArrayList<String> getLabels() {
        return labels;
    }

    public void addLabel(String label) {
        if(label!=null && !label.trim().isEmpty() && !this.labels.contains(label.trim())){
            this.labels.add(label.trim());
        }
    }
    
    public void clearLabels() {
        this.labels.clear();
    }
    
    public String getBestLabelChoice(){
        
        return this.labels.isEmpty() ? "" : this.labels.get(0);
    }
    
    public ArrayList<String> getClasses() {
        return classes;
    }

    public void addClass(String cls) {
        if(cls!=null && !cls.trim().isEmpty() && !this.classes.contains(cls.trim())){
            this.classes.add(cls.trim());
        }
    }
    
    public void clearClasses() {
        this.classes.clear();
    }
    
    public String getClassesAsString(){
        
        return classes.stream()
            // Extract the local name (everything after the last # or /)
            .map(SplitIRI::localname)
            // Sort alphabetically (ascending)
            .sorted()
            // Join into a single string with the given delimiter
            .collect(Collectors.joining(" & "));
    }
    
    
}
