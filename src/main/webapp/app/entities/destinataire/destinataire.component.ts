import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { DestinataireDeleteDialogComponent } from './destinataire-delete-dialog.component';

@Component({
  selector: 'jhi-destinataire',
  templateUrl: './destinataire.component.html',
})
export class DestinataireComponent implements OnInit, OnDestroy {
  destinataires?: IDestinataire[];
  eventSubscriber?: Subscription;

  constructor(
    protected destinataireService: DestinataireService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.destinataireService.query().subscribe((res: HttpResponse<IDestinataire[]>) => (this.destinataires = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDestinataires();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDestinataire): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDestinataires(): void {
    this.eventSubscriber = this.eventManager.subscribe('destinataireListModification', () => this.loadAll());
  }

  delete(destinataire: IDestinataire): void {
    const modalRef = this.modalService.open(DestinataireDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.destinataire = destinataire;
  }
}
