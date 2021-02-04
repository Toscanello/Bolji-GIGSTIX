Vue.component("manifest-search",{
    data:function(){
        return{
            search:{
                naziv:"",
                cena_od:"",
                cena_do:"",
                datum_od:"",
                datum_do:""
            }
        }
    },
    template:`
        <div>
            <h3>Pretraga</h3>

            <input type="text" v-model="search.naziv" placeholder="naziv"/>
            <input type="text" v-model="search.cena_od" placeholder="cena od"/>
            <input type="text" v-model="search.cena_do" placeholder="cena do"/>
            <input type="text" v-model="search.datum_od" placeholder="datum od"/>
            <input type="text" v-model="search.datum_do" placeholder="datum do"/>
            <button type="button" v-on:click="pretrazi()">Pretrazi</button>
        </div>
    `,
    methods:{
        pretrazi:function(){
            this.$emit('clicked',this.search)
        }
    }
})