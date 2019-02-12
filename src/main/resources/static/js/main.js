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
        url:"/s/upload",
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
            url:"/b/addcart",
            data:data,

            success:function(data){
                alert(data);

            },error:function(e){
                console.log(e);
            }
        }
    )
}

function submitorder()
{

    var data=[];
    $('#goodTable tr').each(function(i){
        if(i!=0)
        {
            var tdArr = $(this).find("td");
            var gooid =tdArr.eq(0).attr("id");
            var goodcount =tdArr.eq(1).find("span").eq(1).text();
            var onegood ={}
            onegood['goodid']=parseInt(gooid);
            onegood['number']=parseInt(goodcount);
            data.push(onegood)
        }
    });
    console.log(data)

    $.ajax(
        {
            url:"/b/submitorder",

            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),


            success:function(){
                alert("buy success");
                window.location.href = '/b/carthome';
            },
            error:function()
            {
                alert("error");
            }
    })

}

function deletegoods(id)
{
    $.ajax(
        {
            url:"/s/deletegoods",
            contentType: 'application/json',
            type: 'POST',
            data: id,
            success:function(){
                alert("delete success");
                window.location.href = '/s/sellerHome';
            },
            error:function()
            {
                alert("error");
            }
        })
}
