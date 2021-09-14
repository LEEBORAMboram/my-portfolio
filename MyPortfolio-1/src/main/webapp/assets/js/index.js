$(function(){
    $.ajax({
        type:"get",
        url:"/api/accident/regional/chart?region=서울",
        success:function(r) {
            console.log(r);
            
            let sidoArr = new Array();
            let cnt = new Array();
            
            for(let i=0; i<r.data.length; i++) {
                sidoArr.push(r.data[i].region_sido);
                cnt.push(r.data[i].region_acc_cnt);
            }
            let accChart = new Chart($("#accChart"), {
                    type:'bar',
                    options:{
                        responsive:false,
                    },
                    data:{
                        labels:sidoArr,
                        datasets:[{
                            label:"지역별 교통사고 건 수",
                            data: accChart,
                            backgroundColor:['rgba(30, 20, 205, 0.5)']
                        }
                    ]
                }
            });
        }
    })
    $.ajax({
        type:"get",
        url:"/api/accident/regional/chart?region=서울",
        success:function(r) {
            console.log(r);
            
            let sidoArr = new Array();
            let deathArr = new Array();
             
            for(let i=0; i<r.data.length; i++) {
                sidoArr.push(r.data[i].region_sido);
                deathArr.push(r.data[i].region_death);
            }
            let accDeathChart = new Chart($("#accDeathChart"), {
                    type:'bar',
                    options:{
                        responsive:false,
                    },
                    data:{
                        labels:sidoArr,
                        datasets:[{
                            label:"지역별 교통사고 사망자 수",
                            data: deathArr,
                            backgroundColor:['rgba(255, 30, 30, 0.7)']
                        }
                    ]
                }
            });
        }
    })
    $.ajax({
        type:"get",
        url:"/api/accident/regional/chart?region=서울",
        success:function(r) {
            console.log(r);
            
            let sidoArr = new Array();
            let injuredArr = new Array();
            
            for(let i=0; i<r.data.length; i++) {
                sidoArr.push(r.data[i].region_sido);
                injuredArr.push(r.data[i].region_injured);
            }
            let injuredChart = new Chart($("#injuredChart"), {
                    type:'bar',
                    options:{
                        responsive:false,
                    },
                    data:{
                        labels:sidoArr,
                        datasets:[{
                            label:"지역별 교통사고 부상자 수",
                            data: injuredArr,
                            backgroundColor:['rgba(30, 30, 255, 0.5)']
                        }
                    ]
                }
            });
        }
    })
})