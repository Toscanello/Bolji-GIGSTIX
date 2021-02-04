Vue.component("karta-filter",{
    data: function(){
        return{
          pretraga:{
            
            rast:"",
            opad:"",
            dat_rast:"",
            dat_opad:""

          }
        }
    },

    template:`
        <div>
        
            <input type="checkbox" v-model="reg()">Regular</input>
            <input type="checkbox" v-model="vip()">VIP</input>
            <input type="checkbox" v-model="fpit()">Fan Pit</input>
            
        </div>
    `,
    methods: {
        reg: function(){
            this.$emit('clicked', this.pretraga)
        },
        vip: function(){
            this.$emit('clicked', this.pretraga)
        },
        fpit: function(){
            this.$emit('clicked', this.pretraga)
        }
    }
})