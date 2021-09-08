/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import RequerantService from '@/entities/requerant/requerant.service';
import { Requerant } from '@/shared/model/requerant.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('Requerant Service', () => {
    let service: RequerantService;
    let elemDefault;
    beforeEach(() => {
      service = new RequerantService();

      elemDefault = new Requerant(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Requerant', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Requerant', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Requerant', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            numCNI: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Requerant', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Requerant', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            numCNI: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Requerant', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Requerant', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Requerant', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
