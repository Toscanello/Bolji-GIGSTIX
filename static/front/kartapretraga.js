Vue.component("karta-pretraga",{
    data: function(){
        return{
          pretraga:{
            
            manifestacija:"",
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

            <input type="text" v-model="pretraga.manifestacija" placeholder="manifestacija"/>
            <input type="text" v-model="pretraga.cena_od" placeholder="cena od"/>
            <input type="text" v-model="pretraga.cena_do" placeholder="cena do"/>
            <input type="text" v-model="pretraga.datum_od" placeholder="datum od"/>
            <input type="text" v-model="pretraga.datum_do" placeholder="datum do"/>
            <button type="button" v-on:click="search()">Pretrazi</button>
            
        </div>
    `,
    methods: {
        search: function(){
            this.$emit('clicked', this.pretraga)
        }
    }
})