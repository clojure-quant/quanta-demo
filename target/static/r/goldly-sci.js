(function(){
'use strict';var Bub,Cub,Dub,Eub,Fub,Gub,Hub,Iub,Jub,g8,Lub,f8,Kub,Mub,Oub,Pub,Qub,Rub,Tub,Vub,Yub,Zub,$ub,avb,bvb,cvb,dvb,evb,Sub,fvb,gvb,hvb,Uub,ivb,jvb,h8,kvb,lvb,mvb,nvb,Nub,ovb,d8,pvb,qvb,rvb;Bub=function(){if("undefined"!==typeof performance&&null!=performance.now)return performance.now();if("undefined"!==typeof process&&null!=process.hrtime){var a=process.hrtime();return(1E9*a[0]+a[1])/1E6}return(new Date).getTime()};Cub=function(){return null};
Dub=function(a,b){var c=$APP.JO;$APP.JO=!0;try{$APP.R2.F(a,b)}finally{$APP.JO=c}};Eub=function(a,b,c){$APP.pt.K($APP.Tv.G(a),$APP.Wk,new $APP.I(null,2,5,$APP.J,[$APP.mN,b],null),$APP.il,$APP.D([c]))};Fub=function(a,b,c,d){$APP.pt.ea($APP.Tv.G(a),$APP.Uk,new $APP.I(null,4,5,$APP.J,[$APP.mN,b,$APP.Dx,d],null),c)};Gub=function(a){return $APP.og($APP.Ch.J($APP.jl,$APP.Rk.F($APP.Wi,$APP.Li.G($APP.gEa)),a))};Hub=function(a){a=new $APP.ku(a,$APP.Xe(a));a=new $APP.mu(a);return $APP.su(a)};
Iub=function(a,b){var c=$APP.Oi.J;var d=$APP.pC.G(a);d=$APP.m(d)?d:$APP.Yz.ba();a=c.call($APP.Oi,a,$APP.pC,d);return $APP.Q4(a,b)};
Jub=function(a,b){var c=d8.G(a);b=$APP.u(b);$APP.v(b);b=$APP.z(b);var d=$APP.v(b);b=$APP.z(b);var e=$APP.mt(function(k){return $APP.wf(k)&&$APP.A.F($APP.Gy,$APP.v(k))},b);b=$APP.E.F(e,!0);e=$APP.E.F(e,!1);var f=$APP.St.F(function(k){return $APP.wf(k)&&$APP.A.F($APP.Dy,$APP.v(k))},e);e=function(){var k=$APP.kg([$APP.oN,$APP.r(c)]);$APP.TM(k);try{return Iub(a,new $APP.F(null,$APP.Km,new $APP.F(null,$APP.Mk.J($APP.rm,d,f),new $APP.F(null,$APP.odb,null,1,null),2,null),3,null))}finally{$APP.UM()}}();$APP.oe(c,
e);b=$APP.Ng.K($APP.Ie,$APP.D([b]));return e8(a,b)};
g8=function(a,b){var c=Hub(b),d=function(){var f=d8.G(a);return $APP.m(f)?f:$APP.Ig($APP.r($APP.oN))}(),e=$APP.Oi.J(a,d8,d);return function l(k){function q(){var p=$APP.kg([$APP.oN,$APP.r(d)]);$APP.TM(p);try{p=$APP.mg;var w=$APP.kP(e,c,p);if($APP.H($APP.jP,w)){var x=$APP.E.F(p,$APP.Eu);var y=$APP.m(x)?x:$APP.e3a}else y=w;if($APP.A.F($APP.e3a,y))var B=Promise.resolve(k);else{if($APP.wf(y)){if($APP.A.F($APP.rm,$APP.v(y))){var K=Jub(e,y);var O=f8.G?f8.G(K):f8.call(null,K);var X=l(O)}else X=l(Iub(e,y));
var ba=X}else ba=l(Iub(e,y));B=ba}return B}finally{$APP.UM()}}return $APP.m(function(){var p=!(k instanceof Promise);return p?p:Kub.G?Kub.G(k):Kub.call(null,k)}())?Promise.resolve(k).then(q):q()}(null)};Lub=function(a,b){var c=$APP.Ig(function(){var d=$APP.m(null)?$APP.P.G(null):null;return $APP.m(d)?d:$APP.r($APP.oN)}());a=$APP.Oi.J(a,d8,c);return g8(a,b).then(function(d){return new $APP.h(null,2,[$APP.kl,d,$APP.P,$APP.r(c)],null)})};f8=function(a){a.__sci_await=!0;return a};Kub=function(a){return a.__sci_await};
Mub=function(a){$APP.yC($APP.Zv,"goldly.sci.loader.shadow-add",9,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["webly-shadow-ns load: ",a," type: ",$APP.nd(a)],null)},null),355);return $APP.Lda(a)};
Oub=function(a){a=$APP.ng(a);var b=$APP.E.F(a,$APP.gO),c=$APP.E.F(a,$APP.fO),d=$APP.E.F(a,$APP.P);$APP.E.F(a,$APP.hO);$APP.E.F(a,Nub);$APP.yC($APP.Zv,"goldly.sci.loader.shadow-add",13,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["load-shadow-module: ns: ",c],null)},null),356);var e=new $APP.mj;a=Mub(c);a=$APP.ks(a)?a:$APP.xj(a);$APP.Gj.F($APP.Oj.F(a,function(f){$APP.yC($APP.Zv,"goldly.sci.loader.shadow-add",21,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["received shadow-module for libname: ",
c,"ns: ",d],null)},null),357);$APP.Nda(c)?(f=$APP.Oda(c,f),$APP.yC($APP.Zv,"goldly.sci.loader.shadow-add",24,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,3,5,$APP.J,["ns ",c," is a simple namespace; adding ns.."],null)},null),358),Eub(b,c,f)):($APP.yC($APP.Zv,"goldly.sci.loader.shadow-add",27,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,3,5,$APP.J,["ns ",c," is a sci-configs-namespace ; adding ns .."],null)},null),359),Eub(b,c,f));return $APP.Lj.F(e,$APP.mg)}),function(f){$APP.yC($APP.mx,
"goldly.sci.loader.shadow-add",32,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["no shadow-module received for ns: ",c],null)},null),360);return $APP.gj(e,f)});return e};
Pub=function(a){var b=$APP.Iq,c=$APP.mg;return $APP.At.G(function(d,e){var f=$APP.il.K($APP.D([c,new $APP.h(null,2,[$APP.DI,function(k){$APP.yC($APP.Zv,"goldly.sci.loader.cljs-source-load",17,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["sci source received successfully for url: ",a],null)},null),361);return d.G?d.G(k):d.call(null,k)},$APP.UG,function(k){k.ea?k.ea("sci source load error url: ",a," error: ",k):k.call(null,"sci source load error url: ",a," error: ",k);return e.G?
e.G(k):e.call(null,k)}],null)]));return b.F?b.F(a,f):b.call(null,a,f)})};
Qub=function(a){var b=$APP.yt($APP.yt(a,/\./,"/"),/\-/,"_"),c=$APP.r($APP.lk);a=function(){switch(c instanceof $APP.G?c.ta:null){case "static":return[$APP.n.G($APP.r($APP.nk)),"code/",$APP.n.G(b)].join("");case "dynamic":return["/code/",$APP.n.G(b)].join("");default:return["/code/",$APP.n.G(b)].join("")}}();var d=[$APP.n.G(a),".cljs"].join("");$APP.yC($APP.Zv,"goldly.sci.loader.cljs-source-load",46,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["loading sci-cljs source file from url: ",
d],null)},null),362);return d};Rub=function(a){var b=Qub($APP.n.G(a));$APP.yC($APP.Zv,"goldly.sci.loader.cljs-source-load",52,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,5,5,$APP.J,["load-sci-cljs-code","libname:",a," url: ",b],null)},null),363);return Pub(b)};
Tub=function(a){a=$APP.ng(a);var b=$APP.E.F(a,$APP.gO),c=$APP.E.F(a,$APP.fO),d=$APP.E.F(a,$APP.P),e=$APP.E.F(a,$APP.hO);$APP.yC($APP.JF,"goldly.sci.loader.cljs-source-add",12,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,6,5,$APP.J,["add-sci-cljs-source libname:",c," ns: ",d,"opts: ",e],null)},null),364);var f=new $APP.mj;a=Rub(c);a=$APP.Gj.F($APP.Oj.F(a,function(k){return Lub(b,k)}),function(k){return $APP.Hj(f,new $APP.h(null,1,[Sub,["no sci-code for ns: ",$APP.n.G(c)," err: ",$APP.n.G(k)].join("")],
null))});$APP.Gj.F($APP.Oj.F(a,function(k){var l=$APP.ng(k);$APP.E.F(l,$APP.kl);var q=$APP.E.F(l,$APP.P);$APP.yC($APP.Zv,"goldly.sci.loader.cljs-source-add",23,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["sci-cljs compile result: ",k],null)},null),365);var p=$APP.wy.G(e);$APP.m(p)&&($APP.yC($APP.mx,"goldly.sci.loader.cljs-source-add",27,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,6,5,$APP.J,["registering as: ",p,"in ns: ",q," to:",$APP.Si.G(c)],null)},null),366),Fub(b,
q,$APP.Si.G(c),$APP.wy.G(e)));return $APP.Lj.F(f,new $APP.h(null,1,[$APP.eFa,!1],null))}),function(k){$APP.yC($APP.nx,"goldly.sci.loader.cljs-source-add",31,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["compile error for: ",c," error: ",k],null)},null),367);return $APP.Hj(f,["compile error for: ",$APP.n.G(c)].join(""))});return f};
Vub=function(a){var b=$APP.ng(a);a=$APP.E.F(b,$APP.EA);b=$APP.E.F(b,h8);var c=$APP.ng(b),d=$APP.E.F(c,$APP.jm),e=$APP.E.F(c,$APP.ko),f=$APP.E.F(c,$APP.tv),k=$APP.E.F(c,$APP.zu);c=$APP.E.F(c,$APP.nn);return new $APP.I(null,4,5,$APP.J,[$APP.aVa,new $APP.I(null,2,5,$APP.J,[Uub,a],null),$APP.m(b)?new $APP.I(null,5,5,$APP.J,[$APP.gC,"phase: ",c," type: ",d],null):null,$APP.m(b)?new $APP.I(null,7,5,$APP.J,[$APP.gC,"file: ",k,"line: ",e," column: ",f],null):null],null)};
$APP.tka=function(a,b){return new $APP.I(null,3,5,$APP.J,[$APP.aVa,new $APP.I(null,3,5,$APP.J,[$APP.gC,"sci cljs compile error in file: ",a],null),new $APP.I(null,2,5,$APP.J,[Vub,b],null)],null)};
$APP.Bp=function(a){if($APP.m(Sub.G(a)))return new $APP.h(null,1,[$APP.EA,new $APP.h(null,2,[$APP.vE,"load-error",$APP.Ol,Sub.G(a)],null)],null);var b=$APP.Fs(a),c=function(){var e=$APP.Ol.G(b);return $APP.m(e)?e:a.message}();if($APP.m(c)){var d=function(){var e=$APP.mo.G(b);return $APP.m(e)?e:a.data}();$APP.yC($APP.nx,"goldly.sci.error",50,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["sci error-message:",c," error-data:",d],null)},null),369);return new $APP.h(null,1,[$APP.EA,
new $APP.h(null,3,[$APP.Ol,c,$APP.vE,"",h8,d],null)],null)}return null};$APP.Xq=function(a){try{return new $APP.h(null,1,[$APP.yL,$APP.R4.F(i8,a)],null)}catch(c){var b=c;$APP.yC($APP.nx,"goldly.sci",35,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["sci compile-code --]",a,"[-- ex: ",b],null)},null),370);return new $APP.h(null,1,[$APP.nx,new $APP.h(null,2,[h8,b.data,$APP.EA,b.message],null)],null)}};$APP.Vq=function(a){return $APP.qO(i8,a,!1,null)};
$APP.Wq=function(a){try{var b=$APP.Vq(a)}catch(e){b=null}if($APP.m(b))return $APP.xj(b);var c=new $APP.I(null,1,5,$APP.J,[$APP.Si.G($APP.Xf(a))],null);b=$APP.Uq.K($APP.D([c]));var d=$APP.Oj.F(b,function(){return $APP.Vq(a)});$APP.Gj.F(b,function(e){$APP.yC($APP.nx,"goldly.sci",63,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["requiring-resolve: failed to load ns: ",c," error: ",e],null)},null),371);return null});return d};
$APP.Yq=function(a){try{var b=$APP.kg([$APP.oN,$APP.r(Wub)]);$APP.TM(b);try{var c=Lub(i8,a);return $APP.Gj.F($APP.Oj.F(c,function(e){var f=$APP.ng(e);e=$APP.E.F(f,$APP.kl);f=$APP.E.F(f,$APP.P);var k=new $APP.h(null,5,[$APP.pC,null,$APP.JK,a,$APP.DA,e,$APP.K0,$APP.r(Xub),$APP.P,$APP.n.G(f)],null);$APP.Gg(Wub,f);$APP.Gg(Xub,"");$APP.yC($APP.JF,"goldly.sci",109,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["sci-cljs compile result: ",k],null)},null),376);return k}),function(e){$APP.yC($APP.nx,
"goldly.sci",112,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["sci compile-code-async error:",e],null)},null),377);return $APP.Bp(e)})}finally{$APP.UM()}}catch(e){var d=e;$APP.yC($APP.nx,"goldly.sci",115,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,4,5,$APP.J,["sci compile-code-async2 --]",a,"[-- ex: ",d],null)},null),375);return new $APP.h(null,1,[$APP.nx,new $APP.h(null,2,[h8,d.data,$APP.EA,d.message],null)],null)}};
Yub=function(a){return"number"===typeof a||"string"===typeof a||$APP.Lr(a)||$APP.Mr(a)||null==a||a instanceof RegExp||a instanceof $APP.t||a instanceof $APP.G};Zub=function(a){return $APP.Ch.F($APP.mg,$APP.Og.F($APP.Rk.F(Yub,$APP.bf),$APP.Li.F(function(b){var c=$APP.Mi.G($APP.yt(b,/[A-Z]/,function(d){return["-",$APP.us(d)].join("")}));return new $APP.I(null,2,5,$APP.J,[c,a[b]],null)},Object.getOwnPropertyNames(Object.getPrototypeOf(a)))))};
$APP.uka=function(a){return function(){function b(d,e){var f=null;if(1<arguments.length){f=0;for(var k=Array(arguments.length-1);f<k.length;)k[f]=arguments[f+1],++f;f=new $APP.Ee(k,0,null)}return c.call(this,d,f)}function c(d,e){try{d.preventDefault();d.stopPropagation();var f=d.target,k=f.value,l=Zub(f);$APP.yC($APP.Zv,"goldly.sci.eventhandler",37,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,6,5,$APP.J,["eventhandler v:",k," e-norm: ",l," args: ",e],null)},null),373);var q=new $APP.I(null,
2,5,$APP.J,[k,l],null),p=null==e?q:$APP.Ch.F($APP.dh,$APP.M.F(q,e));$APP.yC($APP.JF,"goldly.sci.eventhandler",42,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["fun-args: ",p],null)},null),374);return $APP.Wj.F(a,p)}catch(x){var w=x;return $APP.yC($APP.nx,"goldly.sci.eventhandler",45,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,2,5,$APP.J,["eventhandler-fn exception: ",w],null)},null),372)}}b.aa=1;b.ca=function(d){var e=$APP.v(d);d=$APP.Ie(d);return c(e,d)};b.K=c;return b}()};
$APP.cma=function(){$APP.yC($APP.Zv,"goldly.sci.init",8,$APP.gC,new $APP.Vi(function(){return new $APP.I(null,1,5,$APP.J,["setting webly.spa.resolver to sci-requiring resolve.."],null)},null),378);return $APP.kla($APP.Wq)};$ub=new $APP.G(null,"disable-arity-checks","disable-arity-checks",1131364206);avb=new $APP.t(null,"compile-sci","compile-sci",-814834508,null);bvb=new $APP.t("cljs.reader","read-string","cljs.reader/read-string",589673466,null);
cvb=new $APP.t("cljs.core","prn","cljs.core/prn",1725204552,null);dvb=new $APP.G(null,"preset","preset",777387345);evb=new $APP.t(null,".toFixed",".toFixed",-895046938,null);Sub=new $APP.G(null,"load-error","load-error",-711926649);fvb=new $APP.t("goldly.sci.clojure-core","time","goldly.sci.clojure-core/time",589711439,null);gvb=new $APP.G(null,"property-path","property-path",201212852);hvb=new $APP.t("cljs.core","system-time","cljs.core/system-time",1562011930,null);
Uub=new $APP.G(null,"p.text-red-500.text-bold","p.text-red-500.text-bold",2083907018);ivb=new $APP.t(null,"compile-sci-async","compile-sci-async",-110238770,null);jvb=new $APP.t(null,"require-async","require-async",-93567966,null);h8=new $APP.G(null,"root-ex","root-ex",-1080814778);kvb=new $APP.t(null,"system-time","system-time",1690633727,null);lvb=new $APP.t(null,"ret__79202__auto__","ret__79202__auto__",-1158508904,null);
mvb=new $APP.t(null,"start__79201__auto__","start__79201__auto__",-957865486,null);nvb=new $APP.G(null,"termination-safe","termination-safe",-1845225130);Nub=new $APP.G(null,"sci-mod","sci-mod",2046385474);ovb=new $APP.t(null,"goldly.sci.clojure-core","goldly.sci.clojure-core",-2000722947,null);d8=new $APP.G(null,"last-ns","last-ns",1405803181);pvb=new $APP.t(null,"resolve-symbol-sci","resolve-symbol-sci",897150083,null);qvb=new $APP.t("cljs.core","-","cljs.core/-",187040141,null);
rvb=new $APP.t(null,"goldly.sci","goldly.sci",-91271036,null);$APP.jr("goldly-sci");var e8=function e8(a,b){var d=d8.G(a);if($APP.u(b)){var e=$APP.v(b);e=$APP.u(e instanceof $APP.t?new $APP.I(null,1,5,$APP.J,[e],null):e);var f=$APP.v(e);e=$APP.z(e);var k=$APP.A.F($APP.vw,f)?$APP.AN:f;f=$APP.Tv.G(a);var l=$APP.lP($APP.r(d));if($APP.m(function(){var y=k instanceof $APP.t;return y?$APP.WGa(a,k):y}())){f=$APP.kg([$APP.oN,$APP.r(d)]);$APP.TM(f);try{$APP.Wj.Ga($APP.jO,a,null,k,e)}finally{$APP.UM()}e=$APP.Ie(b);return e8.F?e8.F(a,e):e8.call(null,a,e)}var q=$APP.ZV.G($APP.r(f));if($APP.m(q)){var p=
$APP.Wj.F($APP.dk,e);e="string"===typeof k?$APP.Uj.J(k,$APP.Fi("\\$"),2):new $APP.I(null,1,5,$APP.J,[k],null);var w=$APP.C.J(e,0,null),x=$APP.C.J(e,1,null);return Promise.resolve(function(){var y=new $APP.h(null,5,[$APP.P,$APP.lP($APP.r(d)),$APP.gO,a,$APP.fO,w,gvb,x,$APP.hO,p],null);return q.G?q.G(y):q.call(null,y)}()).then(function(y){function B(ba,da){var ea=function(){var la=$APP.fO.G(da);return $APP.m(la)?la:k}();ba=$APP.Tv.G(ba);return $APP.pt.F(ba,function(la){var ta=$APP.E.F(la,$APP.mN);ta=
$APP.E.F(ta,ea);return $APP.aO(la,l,ta,ea,p)})}var K=function(){var ba=$APP.gO.G(y);return $APP.m(ba)?ba:a}();if($APP.m($APP.eFa.G(y))){var O=$APP.Ie(b);return e8.F?e8.F(K,O):e8.call(null,K,O)}O=$APP.tu.G(y);if($APP.m(O)){var X=$APP.r(d);return(g8.F?g8.F(K,O):g8.call(null,K,O)).then(function(){$APP.oe(d,X);B(K,y);var ba=$APP.Ie(b);return e8.F?e8.F(K,ba):e8.call(null,K,ba)})}B(K,y);O=$APP.Ie(b);return e8.F?e8.F(K,O):e8.call(null,K,O)})}f=$APP.kg([$APP.oN,$APP.r(d)]);$APP.TM(f);try{$APP.Wj.Ga($APP.jO,
a,null,k,e)}finally{$APP.UM()}e=$APP.Ie(b);return e8.F?e8.F(a,e):e8.call(null,a,e)}return Promise.resolve(null)},j8=function j8(a){for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;c=1<c.length?new $APP.Ee(c.slice(1),0,null):null;return j8.K(arguments[0],c)};j8.K=function(a,b){var c=$APP.Oi.J;var d=d8.G(a);d=$APP.m(d)?d:$APP.Ig($APP.r($APP.oN));a=c.call($APP.Oi,a,d8,d);b=e8(a,b);return f8(b)};j8.aa=1;j8.ca=function(a){var b=$APP.v(a);a=$APP.z(a);return this.K(b,a)};
var svb=$APP.GGa($APP.eZ,j8,!0,!1);var k8;
k8=function(a,b,c){return $APP.Qk.G($APP.u($APP.M.K(new $APP.F(null,$APP.Zz,null,1,null),new $APP.F(null,$APP.eh($APP.Qk.G($APP.u($APP.M.K(new $APP.F(null,mvb,null,1,null),new $APP.F(null,$APP.Qk.G($APP.u($APP.M.G(new $APP.F(null,hvb,null,1,null)))),null,1,null),$APP.D([new $APP.F(null,lvb,null,1,null),new $APP.F(null,c,null,1,null)]))))),null,1,null),$APP.D([new $APP.F(null,$APP.Qk.G($APP.u($APP.M.F(new $APP.F(null,cvb,null,1,null),new $APP.F(null,$APP.Qk.G($APP.u($APP.M.K(new $APP.F(null,$APP.gZ,
null,1,null),new $APP.F(null,"Elapsed time: ",null,1,null),$APP.D([new $APP.F(null,$APP.Qk.G($APP.u($APP.M.K(new $APP.F(null,evb,null,1,null),new $APP.F(null,$APP.Qk.G($APP.u($APP.M.K(new $APP.F(null,qvb,null,1,null),new $APP.F(null,$APP.Qk.G($APP.u($APP.M.G(new $APP.F(null,hvb,null,1,null)))),null,1,null),$APP.D([new $APP.F(null,mvb,null,1,null)])))),null,1,null),$APP.D([new $APP.F(null,6,null,1,null)])))),null,1,null),new $APP.F(null," msecs",null,1,null)])))),null,1,null)))),null,1,null),new $APP.F(null,
lvb,null,1,null)]))))};k8.So=!0;var tvb=$APP.aN($APP.AN,null);$APP.Uq=function Uq(a){for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;c=0<c.length?new $APP.Ee(c.slice(0),0,null):null;return Uq.K(c)};$APP.wa("goldly.sci.require_async",$APP.Uq);$APP.Uq.K=function(a){return $APP.Wj.J(svb,i8,a)};$APP.Uq.aa=0;$APP.Uq.ca=function(a){return this.K($APP.u(a))};$APP.wa("goldly.sci.compile_code",$APP.Xq);var Wub=$APP.Sk.G($APP.r($APP.oN));$APP.hd();
var Xub=$APP.Sk.G(""),l8=function l8(a){for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;c=0<c.length?new $APP.Ee(c.slice(0),0,null):null;return l8.K(c)};l8.K=function(a){console.log.apply(console,$APP.Jj.G(a));a=$APP.Wj.F($APP.n,a);a=[$APP.n.G(a),"\n"].join("");return $APP.pt.J(Xub,$APP.n,a)};l8.aa=0;l8.ca=function(a){return this.K($APP.u(a))};Dub($APP.s3,$APP.Eg(l8));Dub($APP.t3,$APP.Eg($APP.gd));$APP.wa("goldly.sci.compile_code_async",$APP.Yq);
var uvb=$APP.aN($APP.uVa,null),i8,vvb=new $APP.h(null,5,[dvb,new $APP.h(null,1,[nvb,!1],null),$APP.mN,$APP.il.K($APP.D([new $APP.h(null,2,[$APP.AN,new $APP.h(null,5,[$APP.eZ,svb,$APP.a1a,function(){var a=new $APP.Ce(function(){return k8},fvb,$APP.ei([$APP.P,$APP.Q,$APP.zu,$APP.uA,$APP.tv,$APP.ko,$APP.Qz,$APP.vA,$APP.U,$APP.S,$APP.Mx],[ovb,$APP.a1a,"goldly/sci/clojure_core.cljs",28,1,11,!0,11,$APP.N(new $APP.I(null,1,5,$APP.J,[$APP.fW],null)),"Evaluates expr and prints the time it took. Returns the value of expr.",
$APP.m(k8)?k8.Da:null])),b=$APP.r(a),c=$APP.kf(a);a=$APP.m(null)?null:$APP.Q.G(c);var d=new $APP.h(null,4,[$APP.P,tvb,$APP.Q,a,$APP.U,$APP.U.G(c),$APP.S,$APP.S.G(c)],null);return $APP.m($APP.Im.G(c))?$APP.qP(a,b,d):$APP.m(function(){var e=$APP.Qz.G(c);return $APP.m(e)?e:$APP.iN.G(c)}())?$APP.rP(a,b,d):$APP.pP(a,b,d)}(),kvb,function(){var a=new $APP.Ce(function(){return Bub},hvb,$APP.ei([$APP.P,$APP.Q,$APP.zu,$APP.uA,$APP.tv,$APP.ko,$APP.vA,$APP.U,$APP.S,$APP.Mx],[$APP.vw,kvb,"cljs/core.cljs",18,1,
403,403,$APP.N($APP.dh),"Returns highest resolution time offered by host in milliseconds.",$APP.m(Bub)?Bub.Da:null])),b=$APP.r(a),c=$APP.kf(a);a=$APP.m(null)?null:$APP.Q.G(c);var d=new $APP.h(null,4,[$APP.P,tvb,$APP.Q,a,$APP.U,$APP.U.G(c),$APP.S,$APP.S.G(c)],null);return $APP.m($APP.Im.G(c))?$APP.qP(a,b,d):$APP.m(function(){var e=$APP.Qz.G(c);return $APP.m(e)?e:$APP.iN.G(c)}())?$APP.rP(a,b,d):$APP.pP(a,b,d)}(),$APP.i0,$APP.Qs,$APP.bW,function(){var a=new $APP.Ce(function(){return $APP.H1},bvb,$APP.ei([$APP.P,
$APP.Q,$APP.zu,$APP.uA,$APP.Y_,$APP.tv,$APP.ko,$APP.vA,$APP.U,$APP.S,$APP.Mx],[$APP.uVa,$APP.bW,"cljs/reader.cljs",18,new $APP.h(null,6,[$APP.Hx,!1,$APP.Gx,2,$APP.ry,2,$APP.sy,new $APP.I(null,2,5,$APP.J,[new $APP.I(null,1,5,$APP.J,[$APP.$n],null),new $APP.I(null,2,5,$APP.J,[$APP.vZ,$APP.$n],null)],null),$APP.U,$APP.N(new $APP.I(null,1,5,$APP.J,[$APP.$n],null),new $APP.I(null,2,5,$APP.J,[$APP.vZ,$APP.$n],null)),$APP.Wx,$APP.N(null,null)],null),1,174,174,$APP.N(new $APP.I(null,1,5,$APP.J,[$APP.$n],
null),new $APP.I(null,2,5,$APP.J,[$APP.vZ,$APP.$n],null)),"Reads one object from the string s.\n   Returns nil when s is nil or empty.\n\n   Reads data in the edn format (subset of Clojure data):\n   http://edn-format.org\n\n   opts is a map as per cljs.tools.reader.edn/read",$APP.m($APP.H1)?$APP.H1.Da:null])),b=$APP.r(a),c=$APP.kf(a);a=$APP.m(null)?null:$APP.Q.G(c);var d=new $APP.h(null,4,[$APP.P,uvb,$APP.Q,a,$APP.U,$APP.U.G(c),$APP.S,$APP.S.G(c)],null);return $APP.m($APP.Im.G(c))?$APP.qP(a,b,d):
$APP.m(function(){var e=$APP.Qz.G(c);return $APP.m(e)?e:$APP.iN.G(c)}())?$APP.rP(a,b,d):$APP.pP(a,b,d)}()],null),rvb,new $APP.h(null,5,[jvb,$APP.Uq,avb,$APP.Xq,ivb,$APP.Yq,pvb,$APP.Vq,$APP.m0,$APP.Wq],null)],null)])),$APP.BUa,new $APP.h(null,2,[$APP.Yw,window,$APP.KB,$APP.$N],null),$ub,!0,$APP.ZV,function(a){var b=new $APP.mj,c=Oub(a);$APP.Gj.F($APP.Oj.F(c,function(d){return $APP.Lj.F(b,d)}),function(){var d=Tub(a);return $APP.Gj.F($APP.Oj.F(d,function(e){return $APP.Lj.F(b,e)}),function(e){return $APP.gj(b,
e)})});return b}],null),m8=$APP.ng(vvb),wvb=$APP.E.F(m8,$APP.gM),xvb=$APP.E.F(m8,$APP.mN),yvb=$APP.E.F(m8,$APP.cO),zvb=$APP.E.F(m8,$APP.ZN),Avb=$APP.E.F(m8,$APP.zo),Bvb=$APP.E.F(m8,$APP.rv),Cvb=$APP.E.F(m8,$APP.KPa),Dvb=$APP.E.F(m8,$APP.Tv),Evb=$APP.E.F(m8,$APP.Dx),n8=$APP.E.F(m8,$APP.KB),Fvb=$APP.E.F(m8,$APP.eO),Gvb=$APP.E.F(m8,$APP.ZV),Hvb=$APP.E.F(m8,$APP.ML),Ivb=$APP.E.F(m8,$APP.LB),Jvb=$APP.E.F(m8,$APP.yZa),Kvb=$APP.E.F(m8,$APP.BUa),Lvb=$APP.m(Dvb)?Dvb:$APP.Sk.G($APP.mg),Mvb=$APP.il.K($APP.D([$APP.mg,
Evb])),Nvb=$APP.il.K($APP.D([$APP.qjb,yvb])),o8=$APP.il.K($APP.D([$APP.pjb,Kvb])),Ovb;a:for(var Pvb=$APP.fe($APP.nt(o8,new $APP.I(null,1,5,$APP.J,[$APP.KB],null))),Qvb=o8;;){var Rvb=$APP.v(Qvb);if($APP.m(Rvb)){var Svb=Rvb,Tvb=$APP.C.J(Svb,0,null),Uvb=$APP.C.J(Svb,1,null),Vvb=$APP.Fh.J(Pvb,Tvb,$APP.rf(Uvb)?Uvb:new $APP.h(null,1,[$APP.vE,Uvb],null)),Wvb=$APP.Ie(Qvb);Pvb=Vvb;Qvb=Wvb}else{Ovb=new $APP.h(null,2,[$APP.WN,$APP.WN.G(o8),$APP.yN,$APP.he(Pvb)],null);break a}}
(function(a,b,c,d,e,f,k,l,q,p,w){$APP.pt.F(a,function(x){var y=$APP.mN.G(x),B=$APP.Ay.K($APP.il,$APP.D([$APP.m(y)?y:$APP.njb,$APP.m(y)?null:new $APP.h(null,1,[$APP.$Z,$APP.Oi.J(b,$APP.xM,$APP.sfb)],null),d])),K=$APP.il.K($APP.D([c,$APP.Jw.F(x,new $APP.I(null,3,5,$APP.J,[$APP.mN,$APP.$Z,$APP.ML],null))])),O=$APP.Xk.K;B=$APP.Xk.Ga(B,$APP.$Z,$APP.Oi,$APP.ML,K);K=$APP.sN($APP.sEa,$APP.Ds(),new $APP.h(null,1,[$APP.P,$APP.cP],null));y=$APP.M.F($APP.pi(y),$APP.ojb);y=$APP.pN($APP.bO,$APP.Sk.G($APP.Ch.F($APP.OU(),
y)),new $APP.h(null,4,[$APP.S,"A ref to a sorted set of symbols representing loaded libs",$APP.P,$APP.cP,$APP.Xw,!0,$APP.mO,!0],null));O=O.call($APP.Xk,B,$APP.AN,$APP.Oi,$APP.sEa,K,$APP.bO,$APP.D([y]));y=$APP.Dx.G(x);y=$APP.m(y)?$APP.il.K($APP.D([y,k])):k;B=$APP.il.K($APP.D([$APP.cO.G(x),w]));K=$APP.il.K($APP.D([$APP.ZN.G(x),p]));return $APP.jd(x)?new $APP.h(null,4,[$APP.mN,O,$APP.Dx,y,$APP.eO,l,$APP.ZV,q],null):$APP.Oi.K(x,$APP.mN,O,$APP.D([$APP.Dx,y,$APP.eO,l,$APP.ZV,q,$APP.ZN,K,$APP.WN,$APP.WN.G(e),
$APP.yN,$APP.yN.G(e),$APP.YEa,f,$APP.cO,B]))})})(Lvb,Avb,Hvb,xvb,Ovb,o8,Mvb,Fvb,Gvb,zvb,Nvb);i8=$APP.Oi.K(new $APP.h(null,5,[$APP.zo,$APP.mg,$APP.Tv,Lvb,$APP.gM,wvb,$APP.rv,Bvb,$APP.xGa,$APP.m(n8)?n8:Ivb],null),$APP.KB,$APP.m(n8)?Gub($APP.D([n8])):null,$APP.D([$APP.LB,$APP.m(Ivb)?Gub($APP.D([Ivb])):null,$APP.KPa,$APP.m(Cvb)?Cvb:Cub,$APP.yZa,Jvb]));$APP.or();
}).call(this);