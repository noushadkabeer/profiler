<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
            <img src="http://www.google.com/images/logos/ps_logo2.png" alt="" />
            <div id="multiline">
                This is multiline div.
                This is multiline div.
                This is multiline div.
                This is multiline div.
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