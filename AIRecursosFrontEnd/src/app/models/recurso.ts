import { Archivo } from "./archivo";
import { Empresa } from "./empresa";
import { Tecnologia } from "./tecnologia";

export class Recurso {
    idRecurso:number;
    nombre: String;
    primerApellido: String;
    segundoApellido:String;
    tecnologias:Tecnologia[];
    archivo:Archivo;
    descripcion:String;
    tarifa:String;
    empresa:Empresa = {
        idEmpresa: 1,
        nombre: "Kohmi",
        estatus: true
    } ;
    estatus:boolean = true;
    modalidad:String;
    experiencia:String;
    puesto:String;
    ocultar:boolean = false;
}