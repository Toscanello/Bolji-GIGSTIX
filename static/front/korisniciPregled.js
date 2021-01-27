Vue.component("korisnicipregled",{
    data: function(){
        return{
          korisnici:null,
          count:0
        }
    },

    template:`
        <div>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="margin-left: 50px" >
                <div v-for = "m in korisnici">
                
                    <div class="row" >
                        <div class="card shadow-sm" style="width: 300px">
    
                                
                                <p class="card-text">
                                Username: {{m.username}} <br/>
                                Password: {{m.password}} <br/>
                                Ime: {{m.ime}} <br/>
                                Prezime: {{m.prezime}} <br/>
                                Pol: {{m.pol}} <br/>
                                Datum: {{m.datum}} <br/>
                                Role: {{m.uloga}} <br/>
                                </p>
                                
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,
    mounted(){
        axios
        .get('korisnici/getAll')
        .then(response=>{this.korisnici=response.data;})
    },
    methods:{
        increment(){
            this.count += 1;
        }
    }
})