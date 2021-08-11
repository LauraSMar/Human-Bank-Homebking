const app = Vue.createApp({
data(){
    return{
      cards:[],
      clientName:"",
      okProcess:false,
      cardDebito:[],
      cardCredito:[],
      selected:"",
      deleteok:false,
      idcard:"",
      accounts:[],
      selectedpayCard:"",
      checked:"",
      errors: ["Please enter some value"],
     
      
      
     
    };
},
created(){

    
       axios.get("/api/clients/current")   
       .then(res=>{
         this.data=res.data
         this.clientName=this.data.firstName + " " + this.data.lastName
         this.accounts=this.data.accounts
         //console.log(this.accounts)
        // el listado ya incluye las tarjetas borradas logicamente//
         this.cards=this.data.cards.filter(e=>e.deleted==false)
         //console.table(this.cards)
        if(this.cards.length!=0){
         this.cardCredito=this.cards.filter(e=> (e.typeCard=="Credito")&&(e.deleted==false))
         this.cardDebito=this.cards.filter(e=>(e.typeCard=="Debito")&&(e.deleted==false) ) 
        }
        //console.table(this.cardDebito)
        //console.table(this.cardCredito)
        
     

       
       })
       
      
       
     
},

methods:{

  
redireccionar(myid){
  let id=myid
  
  window.location="/web/account.html?id=" + id;
},

redireccionar2(myid){
let id=myid

window.location="/web/loan_aplication.html?id=" + id;
},

  exit(){


    window.location="/web/index.html";
    
    },

    newCard(){
  
      axios.post('/api/clients/current/cards',"typeCard=Debito&colorCard=Blue",{headers:{'content-type':'application/x-www-form-urlencoded'}})
      .then(() =>{ console.log('Created')

                  Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Your new card is ready!',
                    showConfirmButton: false,
                    timer: 2000
                    
                    
                  })
                  
                    //console.log( this.cards)
                    location.reload()
       })
               
      },

      newCardCredit(){
        
        axios.post('/api/clients/current/cards',"typeCard=Credito&colorCard="+this.selected,{headers:{'content-type':'application/x-www-form-urlencoded'}})
        .then(() =>{ console.log('Created Credit Card')
              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Your new card is ready!',
                showConfirmButton: false,
                timer: 2000
                
                
              })
                     
                      //console.log( this.cards)
                      location.reload()
         })
         .catch(error=>{console.log(error)})
         
                 
      },

      CloseModal(){
        
          document.getElementById("exampleModal").style.display = "none";
          location.reload();
      },

      erase(idcard){
      
        //console.log(idcard);
        axios.delete('/api/clients/current/cards/'+ idcard)
        .then(() =>{ console.log('Deleted Credit Card')
        location.reload();          
                     
        
        })
       .catch(error=>{console.log(error)})

  
        
     
      },
      
      checkForm (e) {
        this.errors = [];
        let position2=this.selectedpayCard.length
        let max=parseInt(this.selectedpayCard.slice(16,position2))
       // console.log(max)
       // console.log(this.errors)
    
        if (this.mtopay >max) {
          this.errors.push('You need more money to do this!');
          return this.errors;
        }

        if (this.mtopay!=""&& this.mtopay<=0) {
          this.errors.push('You need a value greater than zero available !');
          return this.errors;
        } 
       
    
      }, 
      

     

payCard(idCard,selectedpayCard,amount){
        
          
          if(this.checked && this.mtopay!=""){
            amount1=this.mtopay
            this.okMsgPost(amount1,idCard)
           

          } else if(!this.checked && selectedpayCard!=""){
            let position2=this.selectedpayCard.length
            amount=this.selectedpayCard.slice(16,position2)
           this.okMsgPost(amount,idCard)
             
          }

},

okMsgPost(amount,idCard){
            if(amount!=0){
                Swal.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, confirm it!'
                    })
                    .then((result) => {
                    if (result.isConfirmed) {
                      console.log("accOrigin="+(this.selectedpayCard).slice(5,13)+"&idCard="+idCard+"&amount="+amount)
                    
                      axios.post("/api/clients/current/payments","accOrigin="+(this.selectedpayCard).slice(5,13)+"&idCard="+idCard+"&amount="+amount,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                  
                          .then(res =>{ console.log("Paying your card")

                          
                                            
                                        Swal.fire({
                                          position: 'top-end',
                                          icon: 'success',
                                          title: 'Your Money was debit from your Account! Please Check it.',
                                          showConfirmButton: false,
                                          timer: 2000
                                          
                                          
                                        })
                                        location.reload()
                                        
                          })
                          .catch(error => { console.log(error)
                                    Swal.fire({
                                      icon: 'error',
                                      title: 'Oops...',
                                      text: 'Something went wrong! Please write to Customer Service',
                                      footer: '<a href = "mailto: customer@humanbank.com">Why do I have this issue?</a>'
                            })

                          })

                    }      
                  })
                }      

}, 

},
computed:{

 


}

  
  

});
app.mount("#app")