import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpiralExpressTestModule } from '../../../test.module';
import { PartenaireComponent } from 'app/entities/partenaire/partenaire.component';
import { PartenaireService } from 'app/entities/partenaire/partenaire.service';
import { Partenaire } from 'app/shared/model/partenaire.model';

describe('Component Tests', () => {
  describe('Partenaire Management Component', () => {
    let comp: PartenaireComponent;
    let fixture: ComponentFixture<PartenaireComponent>;
    let service: PartenaireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [PartenaireComponent],
      })
        .overrideTemplate(PartenaireComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PartenaireComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PartenaireService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Partenaire(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.partenaires && comp.partenaires[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
