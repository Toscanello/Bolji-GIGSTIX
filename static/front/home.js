Vue.component("home-page",{
    data: function(){
        return{
            manifestacije:null
        }
    },
    template: `
    <div>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div v-for = "m in manifestacije">
            
                <div class="row">
                    <div class="card shadow-sm">
                        <img :src="'../images/'+m.slika+'.jpg'" width = "200" heigth = "300">
                        <div class="card-body">
                            <p class="card-text">{{m.naziv}} se odrzava 
                            {{m.datum.date.day}}.{{m.datum.date.month}}.{{m.datum.date.year}}
                            u {{m.datum.time.hour}}:{{m.datum.time.minute}}
                             na lokaciji {{m.lokacija.adresa.ulica}} {{m.lokacija.adresa.broj}}
                              {{m.lokacija.adresa.mesto}} {{m.lokacija.adresa.postBroj}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,
    mounted(){
        axios
        .get('manifestacije/getAll')
        .then(response=>{this.manifestacije=response.data; console.log(this.manifestacije)})
    }
})