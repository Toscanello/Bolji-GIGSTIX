Vue.component("komentari",{
    data:function(){
        return{
            komentar:null
        }
    },
    template:`
    <div>
        <div v-for="k in komentar">
            <div>
                <p>
                    Komentar: {{k.tekst}}
                    Kupac:{{k.kupac.ime}}
                </p> 
                <button type = "button" v-if="(k.aktivan==false)" v-on:click="odobri(k)">Odobri</button>
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