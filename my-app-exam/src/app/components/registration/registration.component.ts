import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';


import { ClientService } from '../../services/client.service';
import { Client } from '../../interfaces/client';
import { MatTableDataSource } from '@angular/material/table';

const clientCollection: Client[] = [];

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  firstName : string = "";
  lastName: string = "";
  phone: string = "";
  email = "";

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'phone', 'email','actions'];
  dataSource = new MatTableDataSource(clientCollection);

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getClients();
  }

 onSave(form: NgForm): void {
  if (form.invalid) {
    form.control.markAllAsTouched();
    alert('Completa los campos obligatorios y corrige los datos marcados.');
    return;
  }

  const client: Client = {
    firstName: this.firstName,
    lastName: this.lastName,
    phone: this.phone,
    email: this.email
  };

  this.clientService.saveClient(client).subscribe({
    next: () => {
      alert('Cliente guardado correctamente.');
      form.resetForm();
      this.getClients();
    },
    error: (error) => {
      alert(`No se pudo guardar: ${error.message}`);
    }
  });
}

deleteClient(id: number): void {
  if (!confirm(`¿Eliminar el cliente ${id}?`)) {
    return;
  }

  this.clientService.deleteClient(id).subscribe({
    next: () => {
      alert('Cliente eliminado correctamente.');
      this.getClients();
    },
    error: (error) => {
      alert(`No se pudo eliminar: ${error.message}`);
    }
  });
}

  getClients(){  
    this.clientService.getClients()
    .subscribe(
      (data) => {
        
        this.dataSource.data = [];
        clientCollection.length = 0;

        data.forEach(function (client: Client, index: number) {
          clientCollection.push(
            {
              id: client.id, 
              firstName: client.firstName,
              lastName: client.lastName, 
              phone: client.phone, 
              email: client.email
            });
        });

         this.dataSource = new MatTableDataSource(clientCollection);
      }
    );
  }
}
