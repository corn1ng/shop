function addlocalbutton()
{
    $("#urlUpload").css("display","none");
    $("#fileUpload").css("display","inline");
}

function addpicbutton()
{

    $("#fileUpload").css("display","none");
    $("#urlUpload").css("display","inline");

}

function imageupload()
{

    var formData = new FormData($( "#form" )[0]);
    $.ajax({
        type:"post",
        url:"/upload",
        data:formData,
        cache: false,
        contentType: false,
        processData: false,
        success:function(data){
            console.log(data);
            alert('上传成功！');
            $("#imgpre").attr("src",'/image/'+data);
            $("#picurl").attr("value",data);
        },error:function(e){
            console.log(e);
        }
    });


}

function addonegood()
{
    var v =$("#allNum").text()

    v++;

    $("#allNum").text(v)
}

function minusonegood()
{
    var v =$("#allNum").text()
    if((v-1)<1)
    {

    }
    else
    {
        v--;
    }

    $("#allNum").text(v)
}

function addcart(goodid)
{


    var number =$("#allNum").text()

    var data ={"goodid":goodid,"number":number};
    $.ajax(
        {
            type:"post",
            url:"/addcart",
            data:data,

            success:function(data){
                alert(data);

            },error:function(e){
                console.log(e);
            }
        }
    )
}