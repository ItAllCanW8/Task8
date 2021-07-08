<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>xml task</title>
  </head>
  <body>
  <h3>XML File Upload</h3>
  <br/>
    <form method="post" action="controller" enctype="multipart/form-data" >
<%--      <input type="hidden" name="command" value="parse">--%>

     <input type="file" name="file" accept=".xml"/> <br/>
      <br/>
      <label for="parser-type">Choose parser type</label>
      <select name="parser-type" id="parser-type">
        <option value="dom">DOM</option>
        <option value="sax">SAX</option>
        <option value="stax">StAX</option>
      </select> <br/>
      <br/>
      <input type="submit" name="upload" />
    </form>
  </body>
</html>
