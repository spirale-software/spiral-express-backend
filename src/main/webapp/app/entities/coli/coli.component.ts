import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IColi } from 'app/shared/model/coli.model';
import { ColiService } from './coli.service';
import { ColiDeleteDialogComponent } from './coli-delete-dialog.component';

@Component({
  selector: 'jhi-coli',
  templateUrl: './coli.component.html',
})
export class ColiComponent implements OnInit, OnDestroy {
  colis?: IColi[];
  eventSubscriber?: Subscription;

  constructor(protected coliService: ColiService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.coliService.query().subscribe((res: HttpResponse<IColi[]>) => (this.colis = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInColis();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IColi): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInColis(): void {
    this.eventSubscriber = this.eventManager.subscribe('coliListModification', () => this.loadAll());
  }

  delete(coli: IColi): void {
    const modalRef = this.modalService.open(ColiDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.coli = coli;
  }
}
