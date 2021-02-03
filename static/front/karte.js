Vue.component("karte",{
    data:function(){
        return{
            korisnik:null,
            karte:null
        }
    },
    template:`
        <div>
            <div v-for="k in karte" style="width:300px">
                <div style="border:1px solid black">
                    <p>Karta:{{k.id}}- {{k.manifestacija.naziv}}</br>
                        Datum:{{k.datum.date.day}}.{{k.datum.date.month}}.{{k.datum.date.year}}
                    </p>
                    <div>
                        <div class="btn-group">
                            <button type = "button" v-if="k.status==='rezervisana'" v-on:click="otkazi(k)">Otkazi</button>
                            <button type ="button" v-on:click="komentarisi(k)">Komentar</button> 
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    `,
    mounted(){
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'))
        axios
        .get(`karte/${this.korisnik.username}`)
        .then(response=>{
            this.karte=response.data
        })
    },
    methods:{
        otkazi:function(k){
            axios
            .post(`/otkazi/${k.id}`)
            .then(response=>{
                alert('Otkazana je karta')
                window.location.reload()
            })
            .catch(error=>{
               alert("Ne mozete otkazati kartu jer manifestacija pocinje uskoro ili je prosla")
               window.location.reload() 
            })
        },
        komentarisi:function(k){
            axios
            .post(`/komentarisi${k.id}`)
            .then(response=>{
                
            })
        }
    }
})