const app = Vue.createApp({
data(){
    return{
      allacc:[],
      transactions:[],
      ok:false,
      clientName:"",
      clientid:"",
      loans:[],
      okToast:false,
      data:[],
      accounts:[],
      fromaccount:"",
      toaccount:"",
      toaccount1:"",
      description:"",
      amount:"",
      typeAccount:"",
      okCreate:false,
      error:[],
      accNumber:"",
      checked:"",
      searchClient:"",
       
     
     
    };
},
created(){

    axios.get("/api/clients/current")
    .then(res =>{

      console.log(res)
      
        this.data=res.data
      // console.log(data)
        this.accounts=this.data.accounts
        console.log(this.accounts)
      // this.transactions=this.data.account[0].transactions
        this.clientName=this.data.firstName + " " + this.data.lastName 
  
     
      })
       
},

methods:{
    

redireccionar(myid){
    let id=myid
    
    window.location="/web/create_cards.html?id=" + id;
},

redireccionar2(myid){
  let id=myid
  
  window.location="/web/loan_aplication.html?id=" + id;
},

exit(){

    window.location="/web/index.html";
    
},

checkTypeAccount(typeAccount){
 

  if(typeAccount!=""){this.newAccount}
  else{Swal.fire('Please select one type of Account, before to create')}

},
newAccount(){
 
        Swal.fire({
          title: 'Are you sure?',
          text: "You won't be able to revert this!",
          icon: 'info',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, create the account!'
            })
            
              .then(result => {
              if (result.isConfirmed) {
              
                axios.post("/api/clients/current/accounts","typeAccount="+(this.typeAccount))
                .then(res =>{ console.log('Created')

                        Swal.fire(
                          'Your Account was created!.',
                          'success'
                        )
                        location.reload()
                                
                })
              }
            })
      
 },


generaPDF(accNumber,dateini,dateend){
 
  //console.log(this.accNumber)
  if(this.dateIni!="" && this.dateEnd!=""){

 //console.log("/api/clients/current/export/pdf","account="+this.accNumber+"&dateIni="+this.dateIni+" 00:00"+"&dateEnd="+ this.dateEnd+" 23:55",{  responseType: 'blob' })
 axios.post("/api/clients/current/export/pdf","accNumber="+this.accNumber+"&dateIni="+this.dateIni+" 00:00"+"&dateEnd="+ this.dateEnd+" 23:55",{  responseType: 'blob' })
  
 
    .then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data],{type: "application/pdf"}));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'Transaction'+ this.accNumber +'.pdf');
        document.body.appendChild(link);
        link.click();
    })
    .catch(err => console.log(err))
  } else{
    Swal.fire(
      'Select two dates to filter!',
      'warnig'
    )
  }
   
},

store(accountNumber){
  this.accNumber=accountNumber
  //console.log(this.accNumber)
  return this.accNumber
},


validateForm(e,amount){
 
  let position2=this.fromaccount.length
 // console.log(this.fromaccount)
  let balance=parseFloat(this.fromaccount.slice(18,position2))
 // console.log(balance)
//  console.log(this.amount)
  this.error = [];



if(this.amount<=0){
  alert("Amount is required and greater than zero");
  //this.amount=balance
 document.getElementById("amount").focus();
  this.error.push('Amount is required');
} 

//console.log(parseFloat(this.amount)>balance)

if(parseFloat(this.amount)>balance) {
 
  alert("This amount is not available in your balance!");
  this.error.push('Balance is not available');
  document.getElementById("amount").focus();
  this.amount=balance
}

if(this.fromaccount==""){
  alert("Origin account is required");
  this.error.push('Account is required');

}

if(!this.checked && this.toaccount==""){
 
    alert("Your destiny account is required");
    this.error.push('Your destiny account is required');

}
if( this.checked && this.toaccount1=="" ){
    alert("Your destiny account is required");
    this.error.push('Your destiny account is required');
}


if( (this.toaccount==this.fromaccount.slice(0,8))|| (this.toaccount1==this.fromaccount.slice(0,8) )){
  alert("Origin and Destiny can't be the same");
  this.error.push('Change your destiny account');
  document.getElementById("toaccount").focus();
}

 if(this.description==""){
  alert("Please put one description");
  this.error.push('Description is required');
  document.getElementById("description").focus();
 }
 //console.log(this.error);
 if(this.error.length==0){this.sendMoney()}


}, 

searchAccDestiny(toaccount1){
  
  
  if(this.toaccount1!=""){
   // console.log("/api/clients/current/search","&accNumber="+this.toaccount1)
  axios.post("/api/clients/current/search","&accNumber="+this.toaccount1)
     .then(response =>{
       console.log(response.data)
       
       this.searchClient=response.data
      // console.log(this.searchClient)
         return this.searchClient

        
      
      
      })
     .catch(err =>{ console.log(err)
      this.searchClient="This client doesn'exist! Check the Number"
      return this.searchClient
    })


          
        
     
    

}

},
     

sendMoney(){
  if(!this.checked){
 // console.log("accOrigin="+(this.fromaccount).slice(0,8)+"&accDestiny="+this.toaccount+"&description="+this.description+"&amount="+Number(this.amount))
  }
  
 // console.log("accOrigin="+(this.fromaccount).slice(0,8)+"&accDestiny="+this.toaccount1+"&description="+this.description+"&amount="+Number(this.amount))
 
  Swal.fire({
    title: 'Are you sure?',
    text: "You gonna send " +this.formatMoney(this.amount)+ " to other account! ",
    icon: 'info',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, Send it!'
  }).then((result) => {
    if (result.isConfirmed) {

      if(!this.checked){
      
        axios.post("/api/clients/current/transactions","accOrigin="+(this.fromaccount).slice(0,8)+"&accDestiny="+this.toaccount+"&description="+this.description+"&amount="+this.amount)
        .then(res =>{ console.log("Transfering")
                  
                
                    Swal.fire(
                      'Your Money was sent!.',
                      'success'
                    )
                    location.reload()
                    
       })
      
      .catch(error => { console.log(error)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong! Please write to Customer Service',
          footer: '<a href = "mailto: customer@humanbank.com">Why do I have this issue?</a>'
      })

      });
      
      }
      if(this.checked){


      axios.post("/api/clients/current/transactions","accOrigin="+(this.fromaccount).slice(0,8)+"&accDestiny="+this.toaccount1+"&description="+this.description+"&amount="+this.amount)
      
      .then(res =>{ console.log("Transfering")
                  
                
                    Swal.fire(
                      'Your Money was sent!.',
                      'success'
                    )
                    location.reload()
                    
       })
      .catch(error => { console.log(error)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong! Please write to Customer Service',
          footer: '<a href = "mailto: customer@humanbank.com">Why do I have this issue?</a>'
      })

      });
      }


      
    }
    
  })
 

},
formatMoney(value){
  if(value==""){
    return ""
  }
  const money=numeral(value).format('$0,0.00')
  return money

}




}



});
app.mount("#app")