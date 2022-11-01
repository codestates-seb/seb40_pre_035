import { useRef } from 'react';
import { Editor as Writer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';
import { fetchUploadImage, BASE_URL } from '../../util/api';

import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';

function Editor() {
  const editorRef = useRef();

  const handleOnChange = () => {
    const data1 = editorRef.current.getInstance().getHTML();
    const data2 = editorRef.current.getInstance().getMarkdown();
    console.log(data1);
    console.log(data2);
  };

  const onUploadImage = async (blob, callback) => {
    console.log(blob);
    let formData = new FormData();
    formData.append('file', blob);
    console.log(formData);

    // let { data: path } =
    let path = await fetchUploadImage(formData).then((data) => {
      console.log(data);
    });

    // console.log(path);
    // callback(path, 'alt text');
    return false;
  };

  return (
    <div className="editor-wrapper">
      <Writer
        previewStyle="tab"
        height="300px"
        initialEditType="markdown"
        initialValue="## *Your* **markdown** here"
        ref={editorRef}
        plugins={[[codeSyntaxHighlight, { highlighter: prism }]]}
        hideModeSwitch={true}
        onChange={handleOnChange}
        useCommandShortcut={false}
        hooks={{
          addImageBlobHook: onUploadImage,
        }}
      />
    </div>
  );
}

export default Editor;
