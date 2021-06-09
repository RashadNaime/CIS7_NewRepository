function getForm(url){
    var info=url.split("?");
    var nameValuePairs=info[1].split("&");
    var $_GET = new Object;
    for(var i=0;i<nameValuePairs.length-1;i++){
        var map=nameValuePairs[i].split("=");
        var name=map[0];
        var value=map[1];
        
        if (value==="")
            value = 0;
        
        $_GET[name]=value;
    }
    return $_GET;
}

function parseURLParams(url) {
    var queryStart = url.indexOf("?") + 1,
        queryEnd   = url.indexOf("#") + 1 || url.length + 1,
        query = url.slice(queryStart, queryEnd - 1),
        pairs = query.replace(/\+/g, " ").split("&"),
        parms = {}, i, n, v, nv;

    if (query === url || query === "") return;

    for (i = 0; i < pairs.length; i++) {
        nv = pairs[i].split("=", 2);
        n = decodeURIComponent(nv[0]);
        v = decodeURIComponent(nv[1]);

        if (!parms.hasOwnProperty(n)) parms[n] = [];
        parms[n].push(nv.length === 2 ? v : null);
    }
    return parms;
}