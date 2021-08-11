const app = Vue.createApp({
data(){
    return{
       email:"",
       password:"",
       firstName:"",
       lastName:"",
       password:"",
       password2:"",
       errors:[],
       noError:false,
      
    };

},
created(){
  
     
      },
   
       
methods:{
    

    send(){
     
      event.preventDefault()
      axios.post('/api/login',"email="+this.email+"&password="+this.password,{headers:{'content-type':'application/x-www-form-urlencoded'}})
      .then(response =>{
        window.location.href="/web/account.html"
        //console.log('signed in!!!')
      /*   Swal.fire({
          title: 'Ready!',
          backdrop: `
            url("/img/gif.gif")
            no-repeat
          `
        }) */


      })

      .catch(err => { 
                        if (err.response) {  console.log(err.response)

                        Swal.fire({
                            title: "Your password is wrong! Please contact 0810-220-BANK",
                            showClass: {
                              popup: 'animate__animated animate__fadeInDown'
                            },
                            hideClass: {
                              popup: 'animate__animated animate__fadeOutUp'
                            }
                          }) 
                          
                        } 
       })

       
      
               
    },
    


    add(){
      console.log("firstName="+this.firstName+"&lastName="+this.lastName+"&email="+this.email+"&password="+this.password)
      event.preventDefault()
     
        if(this.password==this.password2){
    
    
    
           axios.post('/api/clients',"firstName="+this.firstName+"&lastName="+this.lastName+"&email="+this.email+"&password="+this.password,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(response => {
            console.log(this.firstName +'was registered')
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Your login was sucess!',
              showConfirmButton: false,
              timer: 1500
            })

           

            this.noError=true
          
           })

          
          .catch( err => {

            $('#myModal').modal('hide')
            document.getElementById("myModal").remove()

            Swal.fire({
              title: "Your e-mail is already registered! Please contact 0810-220-BANK",
              showClass: {
                popup: 'animate__animated animate__fadeInDown'
              },
              hideClass: {
                popup: 'animate__animated animate__fadeOutUp'
              }
            })
            
            //location.reload()
              
             
          })
 
        }   

   },

   closeForm(){
    document.getElementById("checkForm").reset()
   },

   guardar_localstorage(){

    if(document.getElementById("exampleCheck1").checked){
     let usuario={
       email:this.email,
       password:this.password,
     }
     localStorage.setItem("usuario", JSON.stringify(usuario));

    }
   },
   recuperar_localStorage(){
     if(localStorage.getItem("usuario")){
      return JSON.parse(localStorage.getItem("usuario"))

     }


   },

  
    checkForm: function (e) {

      if (this.firstname && this.lastName) {
        return true;
      }

      this.errors = [];

      if (!this.password && !this.password2) {
        this.errors.push('Password required.');
      }
      if(this.password!= this.password2) {
        this.errors.push('Password are different!');
      }

      e.preventDefault();

      if(this.errors.length==0){

        
        this.add()
      }
    }

    


  },

computed:{

  
}


  });
app.mount("#app")