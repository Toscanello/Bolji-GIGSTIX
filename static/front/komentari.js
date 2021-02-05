Vue.component("komentari",{
    data:function(){
        return{
            komentar:null
        }
    },
    template:`
    <div>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-5 g-5" style="margin-left: 50px" >
            <div v-for="k in komentar" style="heigth:200px">
                <div class="komentar" style="width:250px">
                    <p id="komentar">
                        Komentar: {{k.tekst}}</br>
                        Kupac:{{k.kupac.ime}}<br>
                        Manifestacija:{{k.manifestacija.naziv}}
                    </p> 
                    <button class="btn btn-sm btn-outline-primary" type = "button" v-if="(k.aktivan==false)" v-on:click="odobri(k)">Odobri</button>
                </div>
            </div> 
        </div>
    </div>
    `,
    mounted(){
        axios
        .get("/komentari")
        .then(response=>{
            console.log(response.data)
            this.komentar =response.data 
        })
    },
    methods:{
        odobri:function(k){
            axios
            .post(`/odobri/${k.tekst}`)
            .then(response=>window.location.reload())
        }
    }
})