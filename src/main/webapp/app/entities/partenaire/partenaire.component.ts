import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPartenaire } from 'app/shared/model/partenaire.model';
import { PartenaireService } from './partenaire.service';
import { PartenaireDeleteDialogComponent } from './partenaire-delete-dialog.component';

@Component({
  selector: 'jhi-partenaire',
  templateUrl: './partenaire.component.html',
})
export class PartenaireComponent implements OnInit, OnDestroy {
  partenaires?: IPartenaire[];
  eventSubscriber?: Subscription;

  constructor(protected partenaireService: PartenaireService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.partenaireService.query().subscribe((res: HttpResponse<IPartenaire[]>) => (this.partenaires = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPartenaires();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPartenaire): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPartenaires(): void {
    this.eventSubscriber = this.eventManager.subscribe('partenaireListModification', () => this.loadAll());
  }

  delete(partenaire: IPartenaire): void {
    const modalRef = this.modalService.open(PartenaireDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.partenaire = partenaire;
  }
}
