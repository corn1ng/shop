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
