const app = Vue.createApp({
data(){
    return{
     
      ok:false,
      clientName:"",
      clientid:"",
      loans:[],
      okToast:false,
      data:[],
      alloans:[],
      accounts:[],
      your_account:"",
      amountReq:"",
      loan:"",
      nameLoan:[],
      index:"",
      payments:[],
      myinteres:"",
      error:[],


      
    
      
     
     
    };
},
created(){

    axios.get("/api/clients/current")
    .then(res =>{
        
        this.data=res.data
       //console.log(data)
        this.accounts=this.data.accounts
        console.log(this.accounts)
        this.clientName=this.data.firstName + " " + this.data.lastName
        this.loans=this.data.clientLoan
      //  console.log(this.loans)
        if(this.loans.length!=0){
           this.ok=true
         } 
          if(this.loans.length==0){
          this.okToast=true
        } 
     
      })
      axios.get("/api/loans")
      .then(res =>{ this.alloans=res.data
       //console.log(this.alloans)
      
         
      })   
 
       
},

methods:{

    

redireccionar(myid){
    let id=myid
    
    window.location="/web/create_cards.html?id=" + id;
},

redireccionar2(myid){
  let id=myid
  
  window.location="/web/account.html?id=" + id;
},

exit(){

    window.location="/web/index.html";
    
},

 validateForm(e){
 
  this.error = [];

  if(this.amountReq==""|| this.amountReq<=0){
    alert("You have to Require some Amount and greater than zero");
    this.error.push('Amount not present');
  
  e.preventDefault();
  document.getElementById("amount").focus();
  }

  if(this.amountReq>this.nameLoan.maxAmount){
    alert("The top of this loan is" + this.nameLoan.maxAmount);
  e.preventDefault();
  //console.log(money.formatMoney(amountReq))
  this.amountReq=this.nameLoan.maxAmount
  document.getElementById("amount").focus();
  this.error.push('You is out of limit');
   }

  if(this.payments==""){
    alert("You have to choose some payment");
    e.preventDefault();
    this.error.push('Select a payment for loan');

  }
 if(this.your_account==""){
  alert("Your account is required");
  this.error.push('Your account is required');
  e.preventDefault();
  document.getElementById("your_account").focus();

 }
 //console.log(this.error);
 if(this.error.length==0){this.applytoMoney()}


}, 

applytoMoney(){
 // console.log("accDestiny="+(this.your_account)+"&IdLoan="+this.nameLoan.id+"&reqQuotes="+this.payments+"&amount="+this.amountReq)

  Swal.fire({
    title: 'Are you sure?',
    text: "You won't be able to revert this!",
    text:"Applying for "+ this.formatMoney(this.amountReq) +" to pay in "+this.payments + " monthly fees",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, confirm it!'
  }).then((result) => {
    if (result.isConfirmed) {

      
      //axios.post("/api/clients/current/transactions","accOrigin=VIN15222&accDestiny=VIN00501&description=pago&amount=100")

      axios.post("/api/clients/current/myloans","accDestiny="+(this.your_account)+"&IdLoan="+this.nameLoan.id+"&reqQuotes="+this.payments+"&amount="+this.amountReq)
      .then(res =>{ console.log("Transfering your Loan")
                         
                    Swal.fire(
                      'Your Money was sent to your Account!.',
                      'Please Check it'
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

  })

},

 


cal_quotes(amountReq,payments) {
 

  let pagos=[]
  let interes=[]
  

let id=this.nameLoan.id

//console.log(id)
//console.log(payments)
// console.log( "Este es el indice de cuota" + index)
 /*  console.log("Este es el prestamo" + this.nameLoan.id) */
if(payments!=""){

pagos=this.nameLoan.payments
let index=pagos.findIndex(e=>e==payments)
// console.log(index)

interes=this.nameLoan.interest
myinteres=interes[index]

return(myinteres)
 
}
},



/*calculo de cuotas en el Front y del Form de Aply Loan*/
calcula(amount,quote,interest){
 
  if(quote!=0){

    
    let value=(amount*(1+(interest/100))/quote).toFixed(2)
    return value
    
  }
},

resetForm(){
  document.getElementById("LoanForm").reset()
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