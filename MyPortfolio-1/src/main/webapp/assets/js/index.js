$(function(){
    // $.ajax({
    //     type:"get",
    //     url:"/api/accident/regional",
    //     success:function(r) {
    //         console.log(r);
    //         let cntArr = new Array();
    //         let cntLabel = new Array();
    //         for(let i=0; i<r.data.length; i++) {
    //             cntArr.push(r.data[i].region_death);
    //             cntLabel.push(r.data[i].sido);
    //         }
    //         let regionChart = new Chart($("#regional_chart"),{
    //             type:"bar",
    //             options: {
    //                 responsive:false,
    //             },
    //             data:{
    //                 label:cntLabel,
    //                 datasets:[
    //                     {
    //                         label:"지역별 사고 건 수",
    //                         data:cntArr,
    //                         backgroundColor:["rgba(255,30,30,0.2)"]
    //                     } 
    //                 ]
    //             }
    //         })
    //     }
    // })
})