export class ConfigService {
 
    private urlService:string;
 
    constructor(){
 
        // url do serviço REST criado com o SpringBoot
        this.urlService = 'http://localhost:8090';
    }
 
    getUrlService(): string {
 
        return this.urlService;
    }
 
}