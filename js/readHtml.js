var fs=require('fs'),
system=require('system');
var page = require('webpage').create();
var a=system.args[1];
var b=system.args[2];
var c=system.args[3];
var aaa="http://gkcx.eol.cn/soudaxue/querySpecialtyScore.html?recomzytype=%E5%85%A8%E9%83%A8&recomprovince=%E5%85%A8%E9%83%A8&recomschtype=%E5%85%A8%E9%83%A8&recomschprop=%E5%85%A8%E9%83%A8&recomschoolflag=%E5%85%A8%E9%83%A8&recomyear="+a+"&argprovince="+b+"&recomschoolSortStr=%E9%BB%98%E8%AE%A4&recomluqupici=%E5%85%A8%E9%83%A8&argyear=%E5%85%A8%E9%83%A8&argluqutype=%E5%85%A8%E9%83%A8&schoolSort=5&page="+c+"&scoreSign=3&argkeyword=";
console.log(aaa);
page.open(aaa, function (status) {
    //Page is loaded!
    if (status !== 'success') {
        console.log('Unable to post!');
    } else {
    	window.setTimeout(function () {
    		//console.log(page.content);
    		 try {
    		        fs.write(system.args[4],page.content, 'w');
    		    } catch(e) {
    		        console.log(e);
    		        phantom.exit();
    		    }
			phantom.exit();
        }, 50);
    }   
});

