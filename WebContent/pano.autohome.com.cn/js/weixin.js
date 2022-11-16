var wxutility = { 
    ready2share: function (shareData) {
        wx.error(function (res) {});

        $.ajax({
            type: 'GET',
            data: { url: location.href.split('#')[0] },
            url: "/api/getwxconfig",
            dataType: "json",
            success: function (data) {              
                if (data && data != null && data != "undefined") {                  
                    wx.config({
                        debug: false,
                        appId: data.AppId,
                        timestamp: data.Timestamp,
                        nonceStr: data.NonceStr,
                        signature: data.Signature,
                        jsApiList: [
                            'onMenuShareTimeline',
                            'onMenuShareAppMessage',
                            'onMenuShareQQ',
                            'onMenuShareWeibo',
                            'onMenuShareQZone'
                        ]
                    }); 
                    wx.ready(function () {
                        wx.checkJsApi({
                            jsApiList: [
                            'onMenuShareTimeline',
                            'onMenuShareAppMessage',
                            'onMenuShareQQ',
                            'onMenuShareWeibo',
                            'onMenuShareQZone'
                            ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                            success: function (res) {
                                
                                // 以键值对的形式返回，可用的api值true，不可用为false
                                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                            }
                        });

                        //分享到朋友圈
                        wx.onMenuShareTimeline({
                            title: globalConfig.sharetitle, 
                            link: globalConfig.shareurl, 
                            imgUrl: globalConfig.coverurl,
                            success: function () {                              
                            },
                            cancel: function () {                               
                            },
                            fail: function(e){                               
                            },
                            complete: function(e){                               
                            },
                            trigger: function (e) {                               
                            }
                        });
        
                        //分享给朋友
                        wx.onMenuShareAppMessage({
                            title: globalConfig.sharetitle,
                            desc: globalConfig.sharesummary,
                            link: globalConfig.shareurl,
                            imgUrl: globalConfig.coverurl,
                            success: function () {                               
                            },
                            cancel: function () {                              
                            }
                        });                
                        //分享到QQ
                        wx.onMenuShareQQ({
                            title: globalConfig.sharetitle,
                            desc: globalConfig.sharesummary,
                            link: globalConfig.shareurl,
                            imgUrl: globalConfig.coverurl,
                            success: function () {}
                        });

                        //分享到腾讯微博
                        wx.onMenuShareWeibo({
                            title: globalConfig.sharetitle,
                            desc: globalConfig.sharesummary,
                            link: globalConfig.shareurl,
                            imgUrl: globalConfig.coverurl,
                            success: function () {}
                        });

                        //分享到QQ空间
                        wx.onMenuShareQZone({
                            title: globalConfig.sharetitle,
                            desc: globalConfig.sharesummary,
                            link: globalConfig.shareurl,
                            imgUrl: globalConfig.coverurl,
                            success: function () {}
                        });                        
                    }); 
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {                
            }
        });   

        var sendShare = function (ShareChannel) {
            //$.ajax({
            //    type: 'post',
            //    url: "/consultant/Ajax/weixinShare",
            //    data: { SaleId: shareData.ConsultantId, CompanyId: shareData.companyId, ShareUrl: window.location.href, ShareChannel: ShareChannel },
            //    success: function () {
            //    }
            //});
        };
    }
}

$(document).ready(function () {   
    wxutility.ready2share();
});
