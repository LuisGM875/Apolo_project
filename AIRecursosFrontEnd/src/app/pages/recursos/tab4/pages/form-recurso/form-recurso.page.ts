import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActionSheetController, NavController, NavParams, AlertController, ToastController } from '@ionic/angular';
import { Recurso } from 'src/app/models/recurso';
import { Tecnologia } from 'src/app/models/tecnologia';
import { AlertService } from 'src/app/services/alert.service';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs';
import * as numeral from 'numeral';
import { Archivo } from 'src/app/models/archivo';
import { Select, Store } from '@ngxs/store';
import { CreateRecurso, GetRecursos, UpdateRecurso } from 'src/app/states/recursos/recursos.actions';
import { RecursosState } from 'src/app/states/recursos/recursos.state';
import { CreateArchivo, GetArchivo, UpdateArchivo } from 'src/app/states/archivos/archivos.actions';
import { ArchivosState } from 'src/app/states/archivos/archivos.state';
import { GetTecnologias } from 'src/app/states/tecnologias/tecnologias.actions';
import { TecnologiasState } from 'src/app/states/tecnologias/tecnologias.state';
import { CreateEvaluacion } from 'src/app/states/evaluaciones/evaluaciones.actions';
import { Evaluacion } from 'src/app/models/evaluacion';
import { EvaluacionesState } from 'src/app/states/evaluaciones/evaluaciones.state';
import { DarkModeService } from "../../../../../services/dark-mode";

@Component({
  selector: 'app-form-recurso',
  templateUrl: './form-recurso.page.html',
  styleUrls: ['./form-recurso.page.scss'],
})
export class FormRecursoPage implements OnInit {

  @Select(TecnologiasState.tecnologias)
  tecnologias$: Observable<Tecnologia[]>
  isDarkMode: boolean;

  tecnologiass: String[] = ["Java", "Spring Framework", "MySQL", "Angular", "PostgreSQL", "Ruby"]

  dropDownSettings = {}; // Configuración del ng multiselect dropdown
  formRecurso: FormGroup; // Formulario reactivo
  recurso: Recurso; // Recurso
  tecnologias: Tecnologia[]; // Tecnologias
  file: any; // Archivo subido al input
  update: boolean; // Saber si vamos a actualizar un recurso o no
  changeArchivo: boolean; // Para saber si el archivo ha sido cambiado o no
  archivo: Archivo;
  tarifa: number;
  evaluacion: Evaluacion;

  constructor(
    private navParam: NavParams,
    private navController: NavController,
    private actionController: ActionSheetController,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private store: Store,
    private darkModeService: DarkModeService,
    private alertController: AlertController,
    private toastController: ToastController
  ) {
    this.restartV(); // Reiniciar las variables
  }

  ngOnInit() {
    this.store.dispatch(new GetTecnologias());
    this.getTecnologias(); // LLamada a metodo para obtener las tecnologias
    this.initDropDownSettings();
    this.initRecurso(); // LLamada a metodo para inicializar el formulario

    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });

  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  initDropDownSettings() {
    this.dropDownSettings = {
      singleSelection: false,
      idField: 'idTecnologia',
      textField: 'nombre',
      enableCheckAll: false,
      allowSearchFilter: true,
      clearSearchFilter: true,
      maxHeight: 100,
      itemsShowLimit: 2,
      searchPlaceholderText: 'Buscar Tecnologias',
      noDataAvailablePlaceholderText: 'Principales Tecnologias',
      closeDropDownOnSelection: false,
      showSelectedItemsAtTop: true,
      defaultOpen: false,
    }
  }

  async initRecurso() { // Inicializar el formulario reactivo
    this.recurso = this.navParam.data['recurso'];
    if (!this.recurso) {
      this.recurso = new Recurso;
      this.update = false;
    } else {
      this.update = true;
      this.file = this.recurso.archivo;
    }
    this.formRecurso = this.formBuilder.group({ // Se inicializa el formulario
      idRecurso: new FormControl(this.recurso.idRecurso),
      empresa: new FormControl(this.recurso.empresa),
      estatus: new FormControl(this.recurso.estatus),
      ocultar: new FormControl(this.recurso.ocultar),
      nombre: new FormControl(this.recurso.nombre, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50)]),
      primerApellido: new FormControl(this.recurso.primerApellido, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50)]),
      segundoApellido: new FormControl(this.recurso.segundoApellido, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50)]),
      puesto: new FormControl(this.recurso.puesto, [Validators.required]),
      tecnologias: new FormControl(this.recurso.tecnologias, [Validators.required]),
      modalidad: new FormControl(this.recurso.modalidad, [Validators.required]),
      experiencia: new FormControl(this.recurso.experiencia, [Validators.required]),
      tarifa: new FormControl(this.recurso.tarifa, [Validators.required]),
      descripcion: new FormControl(this.recurso.descripcion, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(100)]),
      archivo: new FormControl(this.recurso.archivo, [Validators.required]),
    });
  }

  createUpdateRecurso() { // Crear o actualizar recurso
    if (this.formRecurso.valid) {
      this.recurso = this.formRecurso.value as Recurso;
      if (this.update) {
        this.updateRecurso();
      } else {
        this.createArchivo() // Creo primero el archivo para que enseguida se inserte el recurso y se haga la relacion con el archivo
      }
    } else {
      this.detectFormErrors();
    }
    this.navParam.data['recurso'] = null;
  }

  async createRecurso() { // Crear recurso
    this.store.dispatch(new CreateRecurso({ recurso: this.recurso })).subscribe({
      next: async () => {
        const success = this.store.selectSnapshot(RecursosState.success);
        if (success) {
          this.recurso = this.store.selectSnapshot(RecursosState.recurso);
          this.createEvaluacion(this.recurso);
          this.playSound('assets/sounds/success.mp3');
          await this.presentAlert('Recurso creado correctamente');
        }
      }, error: async () => {
        await this.alertService.alertError(
          'Error al crear el recurso'
        );
        this.playSound('assets/sounds/error.mp3');
        await this.presentAlert('Error al crear el recurso');
      }
    });
  }

  async updateRecurso() { // Actualizar recurso
    let self = this;
    await this.alertService.alertConfirm(
      '¿Deseas actualizar el recurso?',
      function () {
        if (self.changeArchivo) {
          self.recurso.archivo = self.archivo;
        }
        self.store.dispatch(new UpdateRecurso({ idRecurso: self.recurso.idRecurso, recurso: self.recurso })).subscribe({
          next: () => {
            const success = self.store.selectSnapshot(RecursosState.success);
            if (success) {
              const formData = new FormData();
              formData.append('file', self.file);
              formData.append('nombre', self.recurso.nombre.toString());
              formData.append('idEmpresa', self.recurso.empresa.idEmpresa.toString());
              self.store.dispatch(new UpdateArchivo({ idArchivo: self.recurso.archivo.idArchivo, formData: formData })).subscribe({
                next: async () => {
                  const success = self.store.selectSnapshot(ArchivosState.success);
                  if (success) {
                    await self.alertService.alertSuccess(
                      'Recurso actualizado correctamenta'
                    );
                    self.store.dispatch(new GetRecursos());
                    self.onCloseForm();
                    self.store.dispatch(new GetRecursos());
                    self.onCloseForm();
                    self.playSound('assets/sounds/success.mp3');
                    await self.presentAlert('Recurso actualizado correctamente');
                  }
                }, error: async () => {
                  await self.alertService.alertError(
                    'Error al actualizar el recurso'
                  );
                  self.playSound('assets/sounds/error.mp3');
                  await self.presentAlert('Error al actualizar el recurso');
                }
              });
            }
          }, error: async () => {
            await self.alertService.alertError(
              'Error al actualizar el recurso'
            );
            self.playSound('assets/sounds/error.mp3');
            await self.presentAlert('Error al actualizar el recurso');
          }
        });
      }
    );
  }

  playSound(url: string) {
    const audio = new Audio(url);
    audio.play().catch(error => {
      console.error('Error playing sound:', error);
    });
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'Confirmación',
      message: message,
      buttons: [{
        text: 'OK',
        handler: () => {
          this.onCloseForm();
        }
      }]
    });
    await alert.present();
  }

  createEvaluacion(recurso: Recurso) {
    this.evaluacion = {
      estrella1: 0,
      estrella2: 0,
      estrella3: 0,
      estrella4: 0,
      estrella5: 0,
      promedio: 0,
      totalEvaluaciones: 0,
      estatus: true,
      recurso: recurso
    }

    this.store.dispatch(new CreateEvaluacion({ idRecurso: recurso.idRecurso, evaluacion: this.evaluacion })).subscribe({
      next: async () => {
        const success = this.store.selectSnapshot(EvaluacionesState.success);
        if (success) {
          this.evaluacion = this.store.selectSnapshot(EvaluacionesState.evaluacion);
          await this.alertService.alertSuccess(
            'Recurso creado correctamente'
          );
          this.store.dispatch(new GetRecursos());
          this.onCloseForm();
        }
      }, error: async () => {
        await this.alertService.alertError("Error al crear la evaluacion")
      }
    });

  }

  createArchivo() {
    const formData = new FormData();
    formData.append('file', this.file);
    formData.append('nombre', this.recurso.nombre.toString());
    formData.append('idEmpresa', this.recurso.empresa.idEmpresa.toString());
    this.store.dispatch(new CreateArchivo({ formData: formData })).subscribe({
      next: async () => {
        const success = this.store.selectSnapshot(ArchivosState.success);
        if (success) {
          this.recurso.archivo = this.store.selectSnapshot(ArchivosState.archivo);
          await this.createRecurso();
        }
      }, error: async () => {
        await this.alertService.alertError(
          'Error al crear archivo'
        );
      }
    });
  }

  async actionsArchivo() { // Para presentar acciones del archivo
    const actionSheet = await this.actionController.create({
      header: 'Acciones',
      buttons: [
        {
          text: 'Eliminar',
          icon: 'trash',
          handler: () => {
            this.archivo = this.recurso.archivo;
            this.changeArchivo = true;
            this.recurso.archivo = null;
            this.file = null;
          }
        },
        {
          text: 'Descargar',
          icon: 'download',
          handler: () => {
            this.downloadArchivo(this.recurso)
          }
        },
        {
          text: 'Cancelar',
          icon: 'close',
          role: 'cancel'
        }
      ]
    });
    actionSheet.present();
  }

  getTecnologias() { // Obtener Tecnologias
    this.tecnologias$.subscribe({
      next: () => {
        const success = this.store.selectSnapshot(TecnologiasState.success);
        if (success) {
          this.tecnologias = this.store.selectSnapshot(TecnologiasState.tecnologias) as Tecnologia[];
        }
      }, error: async () => {
        await this.alertService.alertError(
          'Error al cargar las tecnologias'
        );
      }
    });
  }

  onChangeTarifa(event: any) {
    this.tarifa = event.target.value;
    const format = numeral(this.tarifa).format('$0,0');
    this.formRecurso.get('tarifa')?.setValue(format);
  }

  async onCloseForm() { // Metodo para regresar a la pagina de recursos
    this.navController.navigateForward('tabs/tab4');
    this.navParam.data['recurso'] = null;
  }

  async onChangeArchivo(event: any) { // Obtener el archivo que se encuentra en el input tipo file
    if (event.target.files[0].type != "application/pdf") {
      await this.alertService.alertWarning(
        "El archivo debe ser PDF"
      );
      this.formRecurso.get('archivo')?.setValue(null);
    } else {
      this.file = event.target.files[0];
    }
  }

  downloadArchivo(recurso: Recurso) { // Descargar el archivo
    this.store.dispatch(new GetArchivo({ idRecurso: recurso.idRecurso })).subscribe({
      next: () => {
        const success = this.store.selectSnapshot(ArchivosState.success);
        if (success) {
          const blob = this.store.selectSnapshot(ArchivosState.archivoD);
          saveAs(blob, '' + recurso.archivo.nombre);
        }
      }, error: async () => {
        await this.alertService.alertError(
          'Error al descargar el archivo'
        );
      }
    });
  }

  restartV() { // Metodo para reiniciar variables
    this.recurso = null;
    this.archivo = null;
    this.tecnologias = [];
    this.file = "";
    this.update = false;
    this.changeArchivo = false;
    this.tarifa = 0;
    this.evaluacion = null;
  }

  async detectFormErrors() { // Metodo para detectar errores en el formulario reactivo

    let errors = "<ul>";
    if (this.formRecurso.get('nombre').errors && this.formRecurso.get('nombre').errors["required"]) {
      errors += '<li>Digita un nombre valido</li>';
    }

    if (this.formRecurso.get('nombre').errors && this.formRecurso.get('nombre').errors["minlength"]) {
      errors += '<li>Digita un nombre de al menos 4 digitos</li>';
    }

    if (this.formRecurso.get('nombre').errors && this.formRecurso.get('nombre').errors["maxlength"]) {
      errors += '<li>El límite máximo de digitos en el nombre es de 50</li>';
    }

    if (this.formRecurso.get('primerApellido').errors && this.formRecurso.get('primerApellido').errors["required"]) {
      errors += '<li>Digita un primer apellido valido</li>';
    }

    if (this.formRecurso.get('primerApellido').errors && this.formRecurso.get('primerApellido').errors["minlength"]) {
      errors += '<li>Digita un primer apellido de al menos 4 digitos</li>';
    }

    if (this.formRecurso.get('primerApellido').errors && this.formRecurso.get('primerApellido').errors["maxlength"]) {
      errors += '<li>El límite máximo de digitos en el primer apellido es de 50</li>';
    }

    if (this.formRecurso.get('segundoApellido').errors && this.formRecurso.get('segundoApellido').errors["required"]) {
      errors += '<li>Digita un segundo apellido valido</li>';
    }

    if (this.formRecurso.get('segundoApellido').errors && this.formRecurso.get('segundoApellido').errors?.["minlength"]) {
      errors += '<li>Digita un segundo apellido de al menos 4 digitos</li>';
    }

    if (this.formRecurso.get('segundoApellido').errors && this.formRecurso.get('segundoApellido').errors["maxlength"]) {
      errors += '<li>El límite máximo de digitos en el segundo apellido es de 50</li>';
    }

    if (this.formRecurso.get('puesto').errors && this.formRecurso.get('puesto').errors["required"]) {
      errors += '<li>Selecciona un puesto</li>';
    }

    if (this.formRecurso.get('tecnologias').errors && this.formRecurso.get('tecnologias').errors["required"]) {
      errors += '<li>Selecciona al menos una tecnologia</li>';
    }

    if (this.formRecurso.get('modalidad').errors && this.formRecurso.get('modalidad').errors["required"]) {
      errors += '<li>Selecciona una modalidad</li>';
    }

    if (this.formRecurso.get('experiencia').errors && this.formRecurso.get('experiencia').errors["required"]) {
      errors += '<li>Selecciona tu experiencia</li>';
    }

    if (this.formRecurso.get('tarifa').errors && this.formRecurso.get('tarifa').errors["required"]) {
      errors += '<li>Digita una tarifa valida</li>';
    }

    if (this.formRecurso.get('descripcion').errors && this.formRecurso.get('descripcion').errors["required"]) {
      errors += '<li>Digita una descripcion valida</li>';
    }

    if (this.formRecurso.get('descripcion').errors && this.formRecurso.get('descripcion').errors["minlength"]) {
      errors += '<li>Digita una descripcion de al menos 10 digitos</li>';
    }

    if (this.formRecurso.get('descripcion').errors && this.formRecurso.get('descripcion').errors["maxlength"]) {
      errors += '<li>El límite máximo de digitos en la descripcion es de 100</li>';
    }

    if (this.formRecurso.get('archivo').errors && this.formRecurso.get('archivo').errors["required"]) {
      errors += '<li>Envia un cv valido</li>';
    }
    errors += '</ul>';

    await this.alertService.alertError(
      errors
    );
  }
}
