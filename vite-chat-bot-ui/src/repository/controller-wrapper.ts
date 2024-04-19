import { ChatControllerApi, TestControllerApi } from "@generated/chat-server";


type ApiControllerTypes = typeof ChatControllerApi | typeof TestControllerApi;

const createApiController = <T extends ApiControllerTypes>(controller: T): InstanceType<T> =>
  new controller() as InstanceType<T>;

const openApiChatController = createApiController(ChatControllerApi);
const openApiTestController = createApiController(TestControllerApi);

export { openApiChatController, openApiTestController };
