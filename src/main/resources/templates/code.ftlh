<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Code</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
  </head>
  <body>
    <#if code.getTimeRestriction()>
      <span id="time_restriction">${code.getTime()}</span>
    </#if>
    <#if code.getViewsRestriction()>
      <span id="views_restriction">${code.views}</span>
    </#if>
    <pre id="code_snippet"><code>${code.code}</code></pre>
    <span id="load_date">${code.getFormattedDate()}</span>
  </body>
</html>
