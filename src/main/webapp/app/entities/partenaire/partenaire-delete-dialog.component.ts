import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPartenaire } from 'app/shared/model/partenaire.model';
import { PartenaireService } from './partenaire.service';

@Component({
  templateUrl: './partenaire-delete-dialog.component.html',
})
export class PartenaireDeleteDialogComponent {
  partenaire?: IPartenaire;

  constructor(
    protected partenaireService: PartenaireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.partenaireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('partenaireListModification');
      this.activeModal.close();
    });
  }
}
