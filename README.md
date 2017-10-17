# RDFVisualizer-webapp

### 1. Description ###

RDF Visualizer is a tool for displaying and browsing high density of RDF data. This tool is a generic browsing mechanism that gives the user a flexible, highly configurable, detailed overview of an RDF dataset/database, designed and developed to overcome the drawbacks of the existing RDF data visualization methods/tools.

RDF Visualizer supports browsing of content in:
1. Virtuoso database (https://virtuoso.openlinksw.com "virtuoso").
2. Blazegraph database (https://www.blazegraph.com "blazegraph"). 
3. Simple file (Has been tested with .ttl files).
Further information about the tool functionalities can be found here

### 2. Installation ###

3 Simple Steps: Build - Deploy - Run.

The folder src contains all the files needed to build the web app and create a war file.

This project is a Maven project, providing all the libs in pom.xml. 

You may use any application server that supports war files (recommended: Apache Tomcat version 7 and above).

After deployment you will be able to access the following screen in address: [URL]:[PORT]/RDFVisualizer
