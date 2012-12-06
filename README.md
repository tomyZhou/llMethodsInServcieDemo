llMethodsInServcieDemo
======================

讲了三种途径在Activity里面调用Serivce里的方法。
       1、直接在StartService时传入Bundle参数，然后在Serive里面根据传入的参数判断调用哪个方法。
       2、在Activity里面面发送广播，在广播里设置调用哪个方法。
       3、通过BindService