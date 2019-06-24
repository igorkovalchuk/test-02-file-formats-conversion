# test-02-file-formats-conversion

How to use: Main.java 

To support other file formats, please create your own DocumentProcessor implementation
and register it.

ConversionAPI.registerProcessor(DocumentProcessor processor);

This library has two predefined formats: 
 
 * ConversionAPI.FORMAT_XML
 * ConversionAPI.FORMAT_BINARY

Other formats you can define on your own.

Auxiliary classes: FileUtils

TO DO:

 * support huge files (don't load the whole file in memory to parse its content); and the ByteBuffer limit is ~ 2GB file;
 * work with the broken files (where we can fetch at least the first records);
 * more meaningful exceptions, for example, the ParseException should say in which line the exception occurs; and there should be a nice way to process the restrictions like these "... should have positive values" instead of throwing the ParseException;
 * verify the free space before saving a file, http://commons.apache.org/io/apidocs/org/apache/commons/io/FileSystemUtils.html#freeSpaceKb(java.lang.String)
