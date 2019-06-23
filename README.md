# test-02-file-formats-conversion

How to use: Main.java 

To support other file formats, please create your own DocumentProcessor implementation
and register it.

ConversionAPI.registerProcessor(DocumentProcessor processor);

This library has two predefined formats: 
 
 * ConversionAPI.FORMAT_XML
 * ConversionAPI.FORMAT_BINARY

Other formats you can define on your own.

Auxiliary classes: DocumentImpl, CarImpl, FileUtils
