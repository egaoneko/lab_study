<@layout.extends name="layouts/base.ftl">
<@layout.put block="head">
<script src="//ajax.googleapis.com/ajax/libs/mootools/1.4.5/mootools-yui-compressed.js"></script>
</@layout.put>
<@layout.put block="header" type="prepend">
<h2>Index Page</h2>
</@layout.put>
<@layout.put block="contents">
<p>blah.. blah..</p>
</@layout.put>
<@layout.put block="footer" type="replace">
<hr/>
<div class="footer">Footer replaced by index</div>
</@layout.put>
</@layout.extends>