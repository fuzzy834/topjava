<%@page contentType="text/html" pageEncoding="UTF-8" %>

<li class="dropdown" id="lang-dropdown">
    <a class="navbar-link" role="button" data-toggle="dropdown"
       aria-haspopup="true" id="lang-button">${pageContext.response.locale}<span class="caret"></span></a>
    <ul class="dropdown-menu dropdown-menu-right">
        <li><a onclick="show('en')">English</a></li>
        <li><a onclick="show('ru')">Русский</a></li>
    </ul>
</li>
<script type="text/javascript">
    var localeCode="${pageContext.response.locale}";
    function show(lang) {
        window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
    }
</script>