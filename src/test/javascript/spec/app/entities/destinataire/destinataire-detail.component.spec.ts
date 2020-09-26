import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpiralExpressTestModule } from '../../../test.module';
import { DestinataireDetailComponent } from 'app/entities/destinataire/destinataire-detail.component';
import { Destinataire } from 'app/shared/model/destinataire.model';

describe('Component Tests', () => {
  describe('Destinataire Management Detail Component', () => {
    let comp: DestinataireDetailComponent;
    let fixture: ComponentFixture<DestinataireDetailComponent>;
    const route = ({ data: of({ destinataire: new Destinataire(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [DestinataireDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DestinataireDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DestinataireDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load destinataire on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.destinataire).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
