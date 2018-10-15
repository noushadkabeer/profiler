<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title></title>
        <style type="text/css">
            #wrapper {
                width: 100px;
            }
            #top-group {
                white-space: nowrap;
            }
            #wrapper img {
               float: left;
               height: 15px;
            }
            #wrapper #multiline {
               display: inline-block;
               white-space: normal;
            }
            #middle-group {
                clear:both;
            }
        </style>
    </head>
<body>

    <div id="wrapper">
        <div id="top-group">
            <div id="multiline">
    	<s:actionerror /><br/><s:actionmessage />
            </div>
        </div>
        <div id="middle-group">
          This is middle div content
        </div>
        <div id="bottom-group">
           <textarea cols="5" rows="2">
           </textarea>
        </div>
    </div>
</body>
</html>