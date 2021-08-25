$(function() {
    $.ajax({
        type:"get",
        url:"",
        success:function(r){
            console.log(r);

                let regionName = new Array();
                let trafficVolume = new Array();

                for(let i=0; i<10; i++) {
                    let tag = "<tbody class='traffic-tboody'></tbody>";
                    $(".real_traffic_information").append(tag);
                }

                for(let i=0; i<r.data.lenght; i++) {
                    let region = r.data[i].region_sido;
                    let traffic = r.data[i].;
                    regionName.push(region);
                    trafficVolume.push(traffic);

                    console.log(Math.floor(i/3));
                    let page = Math.floor(i/3);
                    let tag = 
                    '<tr>'+
                        '<tr></tr>'+
                        '<tr></tr>'+
                        '<tr></tr>'+
                    +'</tr>'
                    $(".traffic-tboody").append(tag);
                }
                $(".traffic-tboody").eq(0).addClass("active");

                $("#real_traffic_next").click(function(){
                    let currentPage = Number($(".current").html());
                    currentPage++;
                    if(currentPage > 10) currentPage = 10;

                    $(".current").html(currentPage);
                    $(".traffic-tboody").removeClass("active");
                    $(".traffic-tboody").eq(currentPage-1).addClass("active");
                })

                $("#real_traffic_prev").click(function(){
                    let currentPage = Number($(".current").html());
                    currentPage--;
                    if(currentPage < 1) currentPage = 1;

                    $(".current").html(currentPage);
                    $(".traffic-tboody").removeClass("active");
                    $(".traffic-tboody").eq(currentPage-1).addClass("active");
                }
            )
        }
    })    
})
