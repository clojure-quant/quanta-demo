(function(){
'use strict';var lxb,nxb,oxb,pxb,qxb,sxb,txb,vxb,xxb,yxb,r8,Fxb,Gxb,Hxb,Kxb,Lxb,Mxb,Oxb,Jxb,zxb,uxb,Nxb,mxb,Pxb,wxb,Axb,Ixb;lxb=function(a){return $APP.E.F($APP.r(kxb),a)};nxb=function(a){return new $APP.h(null,2,[$APP.$m,a,$APP.pF,new $APP.I(null,4,5,$APP.J,[$APP.D$a,mxb,$APP.Q,a],null)],null)};
oxb=function(){var a=$APP.pi($APP.r(kxb));$APP.Kj.K($APP.D(["devtools pages:",a]));return new $APP.I(null,2,5,$APP.J,[$APP.Nma,new $APP.h(null,3,[$APP.Pwb,"Application",$APP.Qwb,"/",$APP.qA,$APP.Ch.F(new $APP.I(null,1,5,$APP.J,[new $APP.h(null,3,[$APP.$m,"feedback",$APP.jo,"https://github.com/pink-gorilla/goldly/issues",$APP.Nwb,!0],null)],null),$APP.Li.F(nxb,a))],null)],null)};pxb=function(a){return function(){return new $APP.I(null,2,5,$APP.J,[$APP.dJ,a],null)}};
qxb=function(a){var b=$APP.oS.G(pxb("loading page.."));$APP.Oj.F(a,function(c){$APP.Kj.K($APP.D(["devtools page-promise has been resolved!"]));$APP.Kj.K($APP.D(["result: ",c]));return $APP.Gg(b,c)});$APP.Gj.F(a,function(c){$APP.Kj.K($APP.D(["error in resolving devtools page-promise: ",c]));return $APP.Gg(b,pxb(new $APP.I(null,3,5,$APP.J,[$APP.hOa,new $APP.I(null,2,5,$APP.J,[$APP.gC,"devtools page load error!"],null),new $APP.I(null,2,5,$APP.J,[$APP.iOa,$APP.n.G(c)],null)],null)))});return b};
sxb=function(a){$APP.Kj.K($APP.D(["devtools resolving-page: ",a]));var b=$APP.r(rxb);return b.G?b.G(a):b.call(null,a)};
txb=function(a){a=$APP.ng(a);var b=$APP.E.F(a,$APP.rT);$APP.E.F(a,$APP.qT);$APP.E.F(a,$APP.DI);var c=function(){var f=$APP.Q.G(b);return $APP.m(f)?f:"pinkgorilla"}();c=$APP.Ja(c,"/")?$APP.Ei.F(c,1):c;var d=lxb(c);$APP.Kj.K($APP.D(["page name: ",c," page-fn: ",d,"route: ",a]));a=sxb(d);var e=$APP.ks(a)?qxb(a):$APP.oS.G(a);return function(f){return new $APP.I(null,2,5,$APP.J,[$APP.r(e),f],null)}};
$APP.Vla=function(a){return new $APP.I(null,3,5,$APP.J,[$APP.Pla,new $APP.I(null,1,5,$APP.J,[oxb],null),new $APP.I(null,2,5,$APP.J,[txb,a],null)],null)};$APP.qka=function(a){$APP.Kj.K($APP.D(["setting devtools resolver to webly.spa.resolver .."]));var b=$APP.kq();$APP.Gg(rxb,b);$APP.Kj.K($APP.D(["dev-pages menu has : ",$APP.Xe(a)," pages"]));return $APP.Gg(kxb,a)};vxb=function(a){return new $APP.I(null,2,5,$APP.J,[uxb,a],null)};
xxb=function(a,b,c){return new $APP.I(null,3,5,$APP.J,[$APP.wL,new $APP.h(null,1,[$APP.JG,$APP.yv(a,c)],null),new $APP.I(null,2,5,$APP.J,[wxb,new $APP.h(null,1,[$APP.BT,$APP.yv(b,c)],null)],null)],null)};
yxb=function(a){return new $APP.I(null,4,5,$APP.J,[$APP.gC,new $APP.I(null,4,5,$APP.J,[xxb,"https://github.com/pink-gorilla/%s/actions?workflow\x3dCI","https://github.com/pink-gorilla/%s/workflows/CI/badge.svg",a],null),new $APP.I(null,4,5,$APP.J,[xxb,"https://codecov.io/gh/pink-gorilla/%s","https://codecov.io/gh/pink-gorilla/%s/branch/master/graph/badge.svg",a],null),new $APP.I(null,4,5,$APP.J,[xxb,"https://clojars.org/org.pinkgorilla/%s","https://img.shields.io/clojars/v/org.pinkgorilla/%s.svg",
a],null)],null)};r8=function(a,b){return $APP.jf(new $APP.I(null,3,5,$APP.J,[$APP.dJ,new $APP.I(null,2,5,$APP.J,[zxb,a],null),$APP.Ch.F(new $APP.I(null,1,5,$APP.J,[$APP.dJ],null),$APP.Li.F(yxb,b))],null),new $APP.h(null,1,[$APP.vS,!0],null))};
$APP.Wja=function(a){a=$APP.ng(a);$APP.E.F(a,$APP.rT);$APP.E.F(a,$APP.qT);$APP.E.F(a,$APP.DI);return new $APP.I(null,10,5,$APP.J,[$APP.dJ,new $APP.I(null,2,5,$APP.J,[vxb,"devtools"],null),new $APP.I(null,1,5,$APP.J,[Axb],null),new $APP.I(null,2,5,$APP.J,[vxb,"What is pink-gorilla?"],null),new $APP.I(null,4,5,$APP.J,[$APP.YS,new $APP.I(null,2,5,$APP.J,[$APP.ZS,"Can run clj code in the browser. This is done via sci interpreter."],null),new $APP.I(null,2,5,$APP.J,[$APP.ZS,"Via hiccup-fh (functional hiccup) new render functions can be executed from clj."],
null),new $APP.I(null,2,5,$APP.J,[$APP.ZS,"The goldly extension manager will compile your favorite hiccup-fn functions into a precompiled js bundle that is served with goldly"],null)],null),new $APP.I(null,2,5,$APP.J,[vxb,"artefacts"],null),new $APP.I(null,3,5,$APP.J,[r8,"apps and demo",Bxb],null),new $APP.I(null,3,5,$APP.J,[r8,"build tools",Cxb],null),new $APP.I(null,3,5,$APP.J,[r8,"goldly extensions",Dxb],null),new $APP.I(null,3,5,$APP.J,[r8,"notebook (legacy)",Exb],null)],null)};
Fxb=function(a){return"number"===typeof a||"string"===typeof a||$APP.Lr(a)||$APP.Mr(a)||null==a||a instanceof RegExp||a instanceof $APP.t||a instanceof $APP.G};Gxb=function(a){return $APP.Ch.F($APP.mg,$APP.Og.F($APP.Rk.F(Fxb,$APP.bf),$APP.Li.F(function(b){var c=$APP.Mi.G($APP.yt(b,/[A-Z]/,function(d){return["-",$APP.us(d)].join("")}));return new $APP.I(null,2,5,$APP.J,[c,a[b]],null)},Object.getOwnPropertyNames(Object.getPrototypeOf(a)))))};
Hxb=function(a){return function(){function b(d,e){var f=null;if(1<arguments.length){f=0;for(var k=Array(arguments.length-1);f<k.length;)k[f]=arguments[f+1],++f;f=new $APP.Ee(k,0,null)}return c.call(this,d,f)}function c(d,e){try{d.preventDefault();d.stopPropagation();var f=d.target,k=f.value,l=Gxb(f);$APP.Kj.K($APP.D(["eventhandler v:",k," e-norm: ",l," args: ",e]));var q=new $APP.I(null,2,5,$APP.J,[k,l],null),p=null==e?q:$APP.Ch.F($APP.dh,$APP.M.F(q,e));$APP.Kj.K($APP.D(["fun-args: ",p]));return $APP.Wj.F(a,
p)}catch(w){return $APP.Kj.K($APP.D(["eventhandler-fn exception: ",w]))}}b.aa=1;b.ca=function(d){var e=$APP.v(d);d=$APP.Ie(d);return c(e,d)};b.K=c;return b}()};Kxb=function(a){return $APP.Ch.F(new $APP.I(null,1,5,$APP.J,[Ixb],null),$APP.Li.F(function(b){return new $APP.I(null,2,5,$APP.J,[Jxb,b],null)},$APP.m(a)?a:$APP.dh))};
Lxb=function(a,b,c){var d=function(){var e=$APP.pi(b.G?b.G(a):b.call(null,a));return $APP.m(e)?e:new $APP.I(null,1,5,$APP.J,[c],null)}();return $APP.Ch.F(new $APP.I(null,2,5,$APP.J,[$APP.on,new $APP.h(null,2,[$APP.DA,c,$APP.qS,Hxb(function(e){return $APP.kF(new $APP.I(null,3,5,$APP.J,[$APP.u5a,b,e],null))})],null)],null),$APP.Li.F(function(e){return new $APP.I(null,3,5,$APP.J,[$APP.aLa,new $APP.h(null,1,[$APP.DA,e],null),$APP.n.G(e)],null)},d))};
Mxb=function(a){a=$APP.ng(a);var b=$APP.E.F(a,$APP.dS);a=$APP.E.F(a,$APP.LL);return new $APP.I(null,2,5,$APP.J,[$APP.GS,$APP.Ch.F(new $APP.I(null,2,5,$APP.J,[$APP.HS,new $APP.I(null,3,5,$APP.J,[$APP.ES,new $APP.I(null,2,5,$APP.J,[$APP.FS,"component"],null),new $APP.I(null,2,5,$APP.J,[$APP.FS,"theme"],null)],null)],null),$APP.Li.F(function(c){var d=$APP.C.J(c,0,null);c=$APP.C.J(c,1,null);return new $APP.I(null,3,5,$APP.J,[$APP.ES,new $APP.I(null,2,5,$APP.J,[$APP.FS,new $APP.I(null,2,5,$APP.J,[$APP.pS,
d],null)],null),new $APP.I(null,2,5,$APP.J,[$APP.FS,Lxb(b,d,c)],null)],null)},a))],null)};Oxb=function(){var a=$APP.gJ.G(new $APP.I(null,1,5,$APP.J,[$APP.O0a],null)),b=$APP.gJ.G(new $APP.I(null,1,5,$APP.J,[$APP.bOa],null));return function(){return new $APP.I(null,5,5,$APP.J,[$APP.dJ,new $APP.I(null,2,5,$APP.J,[Nxb,"components"],null),new $APP.I(null,2,5,$APP.J,[Mxb,$APP.r(a)],null),new $APP.I(null,2,5,$APP.J,[Nxb,"loaded css"],null),new $APP.I(null,2,5,$APP.J,[Kxb,$APP.r(b)],null)],null)}};
$APP.oka=function(a){a=$APP.ng(a);$APP.E.F(a,$APP.rT);$APP.E.F(a,$APP.qT);$APP.E.F(a,$APP.DI);return new $APP.I(null,2,5,$APP.J,[Pxb,new $APP.I(null,1,5,$APP.J,[Oxb],null)],null)};Jxb=new $APP.G(null,"span.m-1","span.m-1",1609723441);zxb=new $APP.G(null,"h1.text-3xl.text-red-400","h1.text-3xl.text-red-400",-15448778);uxb=new $APP.G(null,"h1.text-xl.text-red-900.mt-5","h1.text-xl.text-red-900.mt-5",1774725594);
Nxb=new $APP.G(null,"p.mt-5.mb-5.text-purple-600.text-3xl","p.mt-5.mb-5.text-purple-600.text-3xl",1212070121);mxb=new $APP.t("pinkgorilla.devtools.core","devtools-page","pinkgorilla.devtools.core/devtools-page",1075942711,null);Pxb=new $APP.G(null,"div.container.mx-auto","div.container.mx-auto",-907582330);wxb=new $APP.G(null,"img.inline-block","img.inline-block",1660842694);Axb=new $APP.G(null,"div.mb-5","div.mb-5",130358228);
Ixb=new $APP.G(null,"div.grid.grid-cols-1.md:grid-cols-2","div.grid.grid-cols-1.md:grid-cols-2",1703623111);$APP.jr("devtools");var rxb=$APP.Sk.G(null),kxb=$APP.Sk.G($APP.mg);var Bxb=new $APP.I(null,4,5,$APP.J,["studio","goldly-docs","pages","demo-goldly"],null),Cxb=new $APP.I(null,3,5,$APP.J,["modular","webly","goldly"],null),Dxb=new $APP.I(null,14,5,$APP.J,"reval ui-repl ui-input ui-site ui-vega ui-highcharts ui-math ui-gorilla ui-quil ui-leaflet ui-cytoscape ui-codemirror ui-markdown ui-binaryclock".split(" "),null),Exb=new $APP.I(null,7,5,$APP.J,"picasso pinkie notebook nrepl-middleware notebook-encoding gorilla-explore kernel-cljs-shadow".split(" "),null);$APP.or();
}).call(this);