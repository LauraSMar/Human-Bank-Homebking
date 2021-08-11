const app = Vue.createApp({
data(){
    return{
       clientes:[],
       firstName:[],
       lastName:[],
       email:[]
      
    };

},
created(){
    axios.get("rest/clients")
    .then(res =>{ 

       this.clientes=res.data._embedded.clients
       console.log(this.clientes)})

       

      
   
},
methods:{
 
  
   add(){
    axios.post("rest/clients", {
        firstName: this.firstName,
        lastName: this.lastName,
        email:this.email,
      })
  

   },

   erase(client){
      
   
       axios.delete(client._links.self.href)
       location.reload()

   }


}


});
app.mount("#app")